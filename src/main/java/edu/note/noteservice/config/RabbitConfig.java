package edu.note.noteservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws IOException {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws IOException {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("note");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("thank");
    }

    @Bean
    public Queue myQueue3() {
        return new Queue("compliance");
    }

    @Bean
    public Queue myQueue4() {
        return new Queue("error");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange-note");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue3()).to(directExchange()).with("compl");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("thnks");
    }
}
