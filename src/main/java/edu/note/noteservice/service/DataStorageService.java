package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;

public interface DataStorageService {
    void saveCompliance(Note note);

    void saveThank(Note note);

    Note getById(Long id);
}
