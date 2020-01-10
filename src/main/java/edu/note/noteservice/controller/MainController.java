package edu.note.noteservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import edu.note.noteservice.statuses.Status;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    private AmqpTemplate template;

    @Autowired
    public MainController(AmqpTemplate template) {
        this.template = template;
    }


    @PostMapping(path = "/note", consumes = "application/json", produces = "application/json")
    public Status addMember(@RequestBody Object note) throws JsonProcessingException {
        String jsonString = new Gson().toJson(note, Map.class);
        Note noteItself = getStringNote((LinkedHashMap) note, jsonString);
        // todo check if null
        template.convertAndSend("note", noteItself);
        return new Status("RECIEVED");
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
