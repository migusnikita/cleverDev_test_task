package com.migus.notes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.migus.notes.entity.CompanyUser;
import com.migus.notes.entity.PatientNote;
import com.migus.notes.entity.PatientProfile;
import com.migus.notes.repository.CompanyUserRepository;
import com.migus.notes.repository.PatientNoteRepository;
import com.migus.notes.repository.PatientProfileRepository;
import com.migus.notes.request.NotesRequest;
import com.migus.notes.response.NoteResponse;
import com.migus.notes.response.NotesListResponse;

@Service
public class ImportNotesService {

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @Autowired
    private PatientNoteRepository patientNoteRepository;

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${oldsystem.api.endpoint.notes}")
    private String oldSystemClientsEndPoint;

    @Value("${oldsystem.api.endpoint.notes}")
    private String oldSystemNotesEndPoint;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportNotesService.class);

    @Scheduled(cron = "${notes.import}")
    @Transactional
    public void importNotes() {
        Iterable<PatientProfile> patientsList = patientProfileRepository.findAll();

        List<PatientNote> notesForImport = getNotesForImport(patientsList);

        patientNoteRepository.saveAll(notesForImport);
    }

    private List<PatientNote> getNotesForImport(Iterable<PatientProfile> patientsList){
        List<PatientNote> notesForImport = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date dateTo = new Date();
        calendar.setTime(dateTo);
        calendar.add(Calendar.HOUR, -2);
        Date dateFrom = calendar.getTime();

        patientsList.forEach(patient -> {
            String clientGuids = patient.getOldClientGuid();
            List<String> guids = new ArrayList<>();
            List<PatientNote> patientNotes = patientNoteRepository.findByPatientProfileId(patient);

            if(clientGuids != null) {
                guids = Arrays.asList(clientGuids.split(","));
            }

            guids.forEach(guid -> addNotesForImport(notesForImport, patientNotes, patient, guid, dateFrom, dateTo));
        });

        return notesForImport;
    }

    private void addNotesForImport(List<PatientNote> notesForImport, List<PatientNote> patientNotes, PatientProfile patient, String guid, Date dateFrom, Date dateTo) {
        List<NoteResponse> notesList = new ArrayList<>();
        NotesRequest notesRequest = new NotesRequest();
        notesRequest.setAgency(patient.getAgency());
        notesRequest.setDateFrom(dateFrom);
        notesRequest.setDateTo(dateTo);
        notesRequest.setClientGuid(patient.getOldClientGuid());
        NotesListResponse clientsListResponse = restTemplate.postForObject(oldSystemNotesEndPoint, notesRequest, NotesListResponse.class);

        if(clientsListResponse != null) {
            notesList = clientsListResponse.getNotes();
        }

        notesList.forEach(note -> {
            PatientNote patientNoteToAdd;
            PatientNote noteToUpdate= patientNotes.stream().filter(patientNote -> note.getClientGuid() == patientNote.getGuid()).findFirst().orElse(null);
            if(noteToUpdate == null) {
                patientNoteToAdd = convertToPatientNote(note, patient);
                notesForImport.add(patientNoteToAdd);
            } else if(compareNotes(note, noteToUpdate)){
                updatePatientNote(noteToUpdate, note);
                notesForImport.add(noteToUpdate);
            }
        });
    }

    private boolean compareNotes(NoteResponse noteResponse, PatientNote noteToCompare) {
        return (noteToCompare.getLastModifiedDateTime().compareTo(noteResponse.getModifiedDateTime()) < 0);
    }

    private PatientNote convertToPatientNote(NoteResponse noteResponse, PatientProfile patient) {
        PatientNote patientNote = new PatientNote();
        patientNote.setGuid(noteResponse.getClientGuid());
        patientNote.setNote(noteResponse.getComments());
        patientNote.setPatientId(patient);
        patientNote.setCreatedDateTime(noteResponse.getCreatedDateTime());
        patientNote.setLastModifiedDateTime(noteResponse.getModifiedDateTime());

        CompanyUser user = companyUserRepository.findByLogin(noteResponse.getLoggedUser());

        if(user == null) {
            LOGGER.error("User was not found in the system");
            return null;
        }

        patientNote.setCreatedByUserId(user);
        patientNote.setLastModifiedByUserId(user);
        return patientNote;
    }

    private PatientNote updatePatientNote(PatientNote noteToUpdate, NoteResponse noteResponse) {
        CompanyUser user = companyUserRepository.findByLogin(noteResponse.getLoggedUser());

        if(user == null) {
            LOGGER.error("User was not found in the system");
            return noteToUpdate;
        }

        noteToUpdate.setLastModifiedByUserId(user);
        noteToUpdate.setLastModifiedDateTime(noteResponse.getModifiedDateTime());
        noteToUpdate.setNote(noteResponse.getComments());
        return noteToUpdate;
    }
}