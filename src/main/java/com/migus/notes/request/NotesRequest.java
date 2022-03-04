package com.migus.notes.request;

import java.util.Date;

public class NotesRequest {

	private String agency;

	private Date dateFrom;

	private Date dateTo;

	private String clientGuid;

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getClientGuid() {
		return clientGuid;
	}

	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}

	@Override
	public String toString() {
		return "NotesRequest [agency=" + agency + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", clientGuid=" + clientGuid + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}