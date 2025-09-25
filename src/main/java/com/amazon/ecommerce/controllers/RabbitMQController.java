package com.amazon.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.services.rabbitMQ.RabbitMQProducer;

@RestController
public class RabbitMQController {
    
    @Autowired
    private RabbitMQProducer rabbitMQProducer; 

    @GetMapping("/send")
    public String sendConfirmMessage (@RequestParam String message){
        rabbitMQProducer.sendMessage(message);
        return "Message sent to RabbitMQ ---- " + message;
    } 
}
