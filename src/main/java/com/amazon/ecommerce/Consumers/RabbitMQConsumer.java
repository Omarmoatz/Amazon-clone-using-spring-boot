package com.amazon.ecommerce.Consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.amazon.ecommerce.config.RabbitMQConfig;


@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE1)
    public void listenOnMessage(String msg){
        System.out.println("------------------------Received Message------------------------ " + msg + " ---from RabbitMQ---");
    }
    
}
