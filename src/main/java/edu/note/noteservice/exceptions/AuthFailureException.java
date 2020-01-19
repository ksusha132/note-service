package edu.note.noteservice.exceptions;

public class AuthFailureException extends RuntimeException {
    public AuthFailureException(String message) {
        super(message);
    }
}
