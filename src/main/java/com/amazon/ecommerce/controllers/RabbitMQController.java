package com.amazon.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.services.rabbitMQ.RabbitMQProducer;

@RestController
public class RabbitMQController {
    
    @Autowired
    private RabbitMQProducer rabbitMQProducer; 

    @PostMapping("/send/direct")
    public String sendConfirmMessage (@RequestBody String message){
        rabbitMQProducer.sendDirectMsg(message);
        return "Direct Message sent to RabbitMQ ---- " + message;
    }

    @PostMapping("/send/fanout")
    public String sendFanoutMessage(@RequestBody String message){
        rabbitMQProducer.sendFanoutMsg(message);
        return "Fanout Message sent to RabbitMQ ---- " + message;
    }
    
    @PostMapping("/send/topic")
    public String sendTopicMessage(@RequestBody String message){
        rabbitMQProducer.sendTopicMsg(message);
        return "topic Message sent to RabbitMQ ---- " + message;
    }
}
