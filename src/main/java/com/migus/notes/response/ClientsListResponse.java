package com.migus.notes.response;

import java.util.List;

public class ClientsListResponse {

	private List<ClientResponse> clients;

	public List<ClientResponse> getClients() {
		return clients;
	}

	public void setClients(List<ClientResponse> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "ClientsListResponse [clients=" + clients + "]";
	}
}
