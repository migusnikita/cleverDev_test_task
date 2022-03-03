package com.migus.notes.response;

import java.util.List;

public class NotesListResponse {

	private List<NoteResponse> notes;

	public List<NoteResponse> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteResponse> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "NotesListResponse [notes=" + notes + "]";
	}
}
