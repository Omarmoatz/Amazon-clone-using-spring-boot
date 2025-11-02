package com.amazon.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.services.rabbitMQ.RabbitMQProducer;

@RestController
public class RabbitMQController {
    
    @Autowired
    private RabbitMQProducer rabbitMQProducer; 

    @GetMapping("/send")
    public String sendConfirmMessage (@RequestBody String message){
        rabbitMQProducer.sendDirectMessage(message);
        return "Direct Message sent to RabbitMQ ---- " + message;
    }

    @PostMapping("/send")
    public String sendFanoutMsg(@RequestBody String message){
        rabbitMQProducer.sendFanoutMessage(message);
        return "Fanout Message sent to RabbitMQ ---- " + message;
    }
}
