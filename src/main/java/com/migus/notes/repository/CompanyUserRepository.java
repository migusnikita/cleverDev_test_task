package com.migus.notes.repository;

import org.springframework.data.repository.CrudRepository;

import com.migus.notes.entity.CompanyUser;

public interface CompanyUserRepository extends CrudRepository<CompanyUser, Long> {

    CompanyUser findByLogin(String login);
}
