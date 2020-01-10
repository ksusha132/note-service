package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.Status;

public interface NoteProcessingService {
    public Status process(Note note);
}
