package com.amazon.ecommerce.services.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.ecommerce.config.RabbitMQConfig;

@Service
public class RabbitMQProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirectMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY, message);
    }

    public void sendFanoutMessage(String msg){
        for(int i=0 ; i<10 ; i++){
            System.out.println("_____sending fanout msg_____ no." + i);
            rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,"", msg);
            sleep(2000);
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting between messages", e);
        }
    }

}
