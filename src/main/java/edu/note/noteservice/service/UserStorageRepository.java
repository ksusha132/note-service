package edu.note.noteservice.service;

import edu.note.noteservice.security.User;

public interface UserStorageRepository {
    User getById(Long id);
}
