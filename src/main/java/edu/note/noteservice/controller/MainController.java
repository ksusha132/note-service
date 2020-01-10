package edu.note.noteservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import edu.note.noteservice.service.DataStorageService;
import edu.note.noteservice.statuses.Status;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    private AmqpTemplate template;
    private DataStorageService dataStorageService;

    @Autowired
    public MainController(AmqpTemplate template, DataStorageService dataStorageService) {
        this.template = template;
        this.dataStorageService = dataStorageService;
    }


    @PostMapping(path = "/postNote", consumes = "application/json", produces = "application/json")
    public Status addMember(@RequestBody Object note) throws JsonProcessingException {
        String jsonString = new Gson().toJson(note, Map.class);
        Note noteItself = getStringNote((LinkedHashMap) note, jsonString);
        if (noteItself != null) {
            template.convertAndSend(noteItself.getType(), noteItself);
        }
        return new Status("RECIEVED");
    }


    @GetMapping(value = "/getNote/{id}", produces = "application/json")
    public String index(@PathVariable Long id) {
        dataStorageService.getById(id);
        return "got test note";
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
