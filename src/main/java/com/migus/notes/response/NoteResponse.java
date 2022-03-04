package com.migus.notes.response;

import java.util.Date;

public class NoteResponse {

	private String comments;

	private String guid;

	private Date modifiedDateTime;

	private String clientGuid;

	private Date datetime;

	private String loggedUser;

	private Date createdDateTime;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getClientGuid() {
		return clientGuid;
	}

	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "NoteResponse [comments=" + comments + ", guid=" + guid + ", modifiedDateTime=" + modifiedDateTime + ", clientGuid=" + clientGuid + ", datetime=" + datetime
				+ ", loggedUser=" + loggedUser + ", createdDateTime=" + createdDateTime + "]";
	}
}
