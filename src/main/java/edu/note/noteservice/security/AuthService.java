package edu.note.noteservice.security;

public interface AuthService {
    boolean authenticate(String token);
}
