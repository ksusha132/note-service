package edu.note.noteservice.exceptions;

public class NotProcessedNoteException extends RuntimeException {
    public NotProcessedNoteException(String message) {
        super(message);
    }
}
