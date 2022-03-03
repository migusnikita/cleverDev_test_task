package com.migus.notes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CompanyUser extends AbstractEntity {

	@Column(name = "login", unique = true)
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "CompanyUser [login=" + login + "]";
	}
}
