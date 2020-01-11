package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import edu.note.noteservice.statuses.Status;
import org.springframework.stereotype.Service;

@Service
public class NoteThankProcessingServiceImpl implements NoteProcessingService {

    private DataStorageRepository dataStorageRepository;

    public NoteThankProcessingServiceImpl(DataStorageRepository dataStorageRepository) {
        this.dataStorageRepository = dataStorageRepository;
    }

    @Override
    public Status process(Note note) throws InterruptedException {
        Thread.sleep(1000);
        dataStorageRepository.saveThank((ThankNote) note);
        return new Status("Cool");
    }
}
