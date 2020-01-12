package edu.note.noteservice.statuses;

public enum NoteType {
    COMPLIANCE("compliance"),
    THANK("thank");

    private String noteType;

    NoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getNoteType() {
        return noteType;
    }
}
