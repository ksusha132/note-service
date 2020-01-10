package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.Status;
import org.springframework.stereotype.Service;

@Service
public class NoteProcessingServiceImpl implements NoteProcessingService {
    @Override
    public Status process(Note note) {
        Status status = new Status();
        status.setStatus("Ok");
        return status;
    }
}
