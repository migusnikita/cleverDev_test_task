package com.migus.notes.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.migus.notes.entity.PatientNote;
import com.migus.notes.entity.PatientProfile;
import com.migus.notes.repository.PatientNoteRepository;
import com.migus.notes.repository.PatientProfileRepository;
import com.migus.notes.request.NotesRequest;
import com.migus.notes.response.ClientResponse;
import com.migus.notes.response.ClientsListResponse;
import com.migus.notes.response.NoteResponse;
import com.migus.notes.response.NotesListResponse;

@Service
public class ImportNotesService {

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @Autowired
    private PatientNoteRepository patientNoteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${oldsystem.api.endpoint.notes}")
    private String oldSystemClientsEndPoint;

    @Value("${oldsystem.api.endpoint.notes}")
    private String oldSystemNotesEndPoint;

    @Scheduled(cron = "${notes.import}")
    @Transactional
    public void importNotes() {
        List<ClientResponse> clientsList = null;
        ClientsListResponse clientsListResponse = restTemplate.postForObject(oldSystemClientsEndPoint, null, ClientsListResponse.class);

        if (clientsListResponse != null) {
            clientsList = clientsListResponse.getClients();
        }

        Iterable<PatientProfile> patientsList = patientProfileRepository.findAll();

        List<PatientNote> notesForImport = getNotesForImport(patientsList);

        patientNoteRepository.saveAll(notesForImport);
    }

    private List<PatientNote> getNotesForImport(Iterable<PatientProfile> patientsList) {
        List<PatientNote> notesForImport = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date dateTo = new Date();
        calendar.setTime(dateTo);
        calendar.add(Calendar.HOUR, -1);
        Date dateFrom = calendar.getTime();

        patientsList.forEach(patient -> {
            List<NoteResponse> notesList = null;
            NotesRequest notesRequest = new NotesRequest();
            notesRequest.setAgency(patient.getAgency());
            notesRequest.setDateFrom(dateFrom);
            notesRequest.setDateTo(dateTo);
            notesRequest.setClientGuid(patient.getOldClientGuid());

            NotesListResponse clientsListResponse = restTemplate.postForObject(oldSystemNotesEndPoint,
                    notesRequest,
                    NotesListResponse.class);

            if (clientsListResponse != null) {
                notesList = clientsListResponse.getNotes();
            }
        });

        return notesForImport;
    }

}
