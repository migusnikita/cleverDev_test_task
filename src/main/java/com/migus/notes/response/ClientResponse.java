package com.migus.notes.response;

import java.util.Date;

public class ClientResponse {

    private String agency;

    private String guid;

    private String firstName;

    private String lastName;

    private String status;

    private Date dob;

    private Date createdDateTime;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "ClientResponse [agency=" + agency + ", guid=" + guid + ", firstName=" + firstName + ", lastName=" + lastName + ", status=" + status + ", dob=" + dob
                + ", createdDateTime=" + createdDateTime + "]";
    }
}
