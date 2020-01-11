package edu.note.noteservice.service;

import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;

public interface DataStorageRepository {
    void saveCompliance(ComplianceNote note);

    void saveThank(ThankNote note);

    Note getById(Long id);
}
