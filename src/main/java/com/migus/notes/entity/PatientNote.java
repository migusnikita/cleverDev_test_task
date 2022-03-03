package com.migus.notes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class PatientNote extends AbstractEntity{

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date_time")
	@CreatedDate
	private Date createdDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date_time")
	@LastModifiedDate
	private Date lastModifiedDateTime;
	
	@JoinColumn(name = "created_by_user_id")
	@ManyToOne
	private CompanyUser createdByUserId;
	
	@JoinColumn(name = "last_modified_by_user_id")
	@ManyToOne
	private CompanyUser lastModifiedByUserId;
	
	@Column(name = "note")
	private String note;
	
	@JoinColumn(name = "patient_id")
	@ManyToOne
	private PatientProfile patientId;

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public CompanyUser getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(CompanyUser createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public CompanyUser getLastModifiedByUserId() {
		return lastModifiedByUserId;
	}

	public void setLastModifiedByUserId(CompanyUser lastModifiedByUserId) {
		this.lastModifiedByUserId = lastModifiedByUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PatientProfile getPatientId() {
		return patientId;
	}

	public void setPatientId(PatientProfile patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "PatientNote [createdDateTime=" + createdDateTime + ", lastModifiedDateTime=" + lastModifiedDateTime + ", createdByUserId=" + createdByUserId
				+ ", lastModifiedByUserId=" + lastModifiedByUserId + ", note=" + note + ", patientId=" + patientId + "]";
	}
}
