package com.migus.notes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PatientProfile extends AbstractEntity {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last")
	private String lastName;
	
	@Column(name = "old_client_guid")
	private String oldClientGuid;
	
	@Column(name = "status_id")
	private int statusId;
	
	@Column(name = "agency")
	private String agency;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOldClientGuid() {
		return oldClientGuid;
	}

	public void setOldClientGuid(String oldClientGuid) {
		this.oldClientGuid = oldClientGuid;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "PatientProfile [firstName=" + firstName + ", lastName=" + lastName + ", oldClientGuid=" + oldClientGuid + ", statusId=" + statusId + ", agency=" + agency + "]";
	}
}
