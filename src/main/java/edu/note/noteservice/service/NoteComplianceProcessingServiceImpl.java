package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteComplianceProcessingServiceImpl implements NoteProcessingService {


    private DataStorageService dataStorageService;

    public NoteComplianceProcessingServiceImpl(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @Override
    public Status process(Note note) throws InterruptedException {
        Thread.sleep(1000);
        dataStorageService.saveCompliance(note);
        return new Status("OK");
    }
}
