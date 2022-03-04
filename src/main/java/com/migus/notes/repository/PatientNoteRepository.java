package com.migus.notes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.migus.notes.entity.PatientNote;
import com.migus.notes.entity.PatientProfile;

public interface PatientNoteRepository extends CrudRepository<PatientNote, Long> {

    List<PatientNote> findByPatientProfileId(PatientProfile patientProfile);
}
