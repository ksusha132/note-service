package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.Status;
import org.springframework.stereotype.Service;

@Service
public class NoteThankProcessingServiceImpl implements NoteProcessingService {

    private DataStorageService dataStorageService;

    public NoteThankProcessingServiceImpl(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @Override
    public Status process(Note note) throws InterruptedException {
        Thread.sleep(1000);
        dataStorageService.saveThank(note);
        return new Status("Cool");
    }
}
