package edu.note.noteservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.note.noteservice.exceptions.NotProcessedNoteException;
import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import edu.note.noteservice.security.Authenticated;
import edu.note.noteservice.service.DataStorageRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    private AmqpTemplate template;
    private DataStorageRepository dataStorageRepository;

    @Autowired
    public MainController(AmqpTemplate template, DataStorageRepository dataStorageRepository) {
        this.template = template;
        this.dataStorageRepository = dataStorageRepository;
    }


    @PostMapping(path = "/postNote", consumes = "application/json", produces = "application/json")
    @Authenticated
    public HttpStatus addMember(HttpServletRequest request, @RequestBody Object note) throws JsonProcessingException {
        String jsonString = new Gson().toJson(note, Map.class);
        Note noteItself = getStringNote((LinkedHashMap) note, jsonString);
        if (noteItself != null) {
            template.convertAndSend(noteItself.getType(), noteItself);
        }
        return HttpStatus.ACCEPTED;
    }


    @GetMapping(value = "/getNote/{id}", produces = "application/json")
    @Authenticated
    public Note index(HttpServletRequest request, @PathVariable Long id) {
        try {
            return dataStorageRepository.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotProcessedNoteException("This note has not processed yet");
        }
    }


    private Note getStringNote(LinkedHashMap note, String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String noteType = note.get("type").toString();
        Note noteItself = null;
        switch (noteType) {
            case "compliance":
                noteItself = mapper.readValue(jsonString, ComplianceNote.class);
                break;
            case "thank":
                noteItself = mapper.readValue(jsonString, ThankNote.class);
                break;
        }
        return noteItself;
    }
}
