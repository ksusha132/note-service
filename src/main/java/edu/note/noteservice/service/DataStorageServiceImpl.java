package edu.note.noteservice.service;

import edu.note.noteservice.note.Note;
import org.springframework.stereotype.Service;

@Service
public class DataStorageServiceImpl implements DataStorageService {

    // jdbc template

    @Override
    public void saveCompliance(Note note) {

    }

    @Override
    public void saveThank(Note note) {

    }

    @Override
    public Note getById(Long id) {
        return null;
    }
}
