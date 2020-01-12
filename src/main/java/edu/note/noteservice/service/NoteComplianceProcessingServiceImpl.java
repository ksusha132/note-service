package edu.note.noteservice.service;

import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.Status;
import org.springframework.stereotype.Service;

@Service
public class NoteComplianceProcessingServiceImpl implements NoteProcessingService {


    private DataStorageRepository dataStorageRepository;

    public NoteComplianceProcessingServiceImpl(DataStorageRepository dataStorageRepository) {
        this.dataStorageRepository = dataStorageRepository;
    }

    @Override
    public Status process(Note note) throws InterruptedException {
        Thread.sleep(1000);
        dataStorageRepository.saveCompliance((ComplianceNote) note);
        return Status.OK;
    }
}
