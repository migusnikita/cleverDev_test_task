package com.migus.notes.repository;

import org.springframework.data.repository.CrudRepository;

import com.migus.notes.entity.PatientNote;

public interface PatientNoteRepository extends CrudRepository<PatientNote, Long> {

}
