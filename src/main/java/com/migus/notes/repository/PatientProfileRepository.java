package com.migus.notes.repository;

import org.springframework.data.repository.CrudRepository;

import com.migus.notes.entity.PatientProfile;

public interface PatientProfileRepository extends CrudRepository<PatientProfile, Long>{

}
