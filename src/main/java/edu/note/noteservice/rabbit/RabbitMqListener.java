package edu.note.noteservice.rabbit;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.service.NoteProcessingService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    private Logger logger = Logger.getLogger(RabbitMqListener.class);

    private final NoteProcessingService noteComplianceProcessingServiceImpl;
    private final NoteProcessingService noteThankProcessingServiceImpl;

    public RabbitMqListener(@Qualifier("noteComplianceProcessingServiceImpl") NoteProcessingService noteComplianceProcessingServiceImpl,
                            @Qualifier("noteThankProcessingServiceImpl") NoteProcessingService noteThankProcessingServiceImpl) {
        this.noteComplianceProcessingServiceImpl = noteComplianceProcessingServiceImpl;
        this.noteThankProcessingServiceImpl = noteThankProcessingServiceImpl;
    }


    @RabbitListener(queues = "compliance")
    public void processQueue1(Message message) throws InterruptedException {
        Note note = (Note) SerializationUtils.deserialize(message.getBody());
        noteComplianceProcessingServiceImpl.process(note); //
    }

    @RabbitListener(queues = "thank")
    public void processQueue2(Message message) throws InterruptedException {
        Note note = (Note) SerializationUtils.deserialize(message.getBody());
        noteThankProcessingServiceImpl.process(note);
    }

    @RabbitListener(queues = "error")
    public void processQueue3(Message message) {
        Note note = (Note) SerializationUtils.deserialize(message.getBody());
        logger.error("Bad messages came :" + note.getId());
    }
}
