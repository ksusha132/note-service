package edu.note.noteservice.statuses;

public enum Status {
    RECEIVED("received"),
    ERROR("error"),
    OK("ok");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getNoteType() {
        return status;
    }
}
