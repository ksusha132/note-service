package edu.note.noteservice.rabbit;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.service.NoteProcessingService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    final
    private NoteProcessingService processingService;

    @Autowired
    public RabbitMqListener(NoteProcessingService processingService) {
        this.processingService = processingService;
    }

    @RabbitListener(queues = "note")
    public void processQueue1(Message message) {
        Note note = (Note) SerializationUtils.deserialize(message.getBody());
        processingService.process(note);
    }

}
