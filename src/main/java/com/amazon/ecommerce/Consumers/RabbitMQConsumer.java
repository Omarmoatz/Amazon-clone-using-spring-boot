package com.amazon.ecommerce.Consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.amazon.ecommerce.config.RabbitMQConfig;


@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE1)
    public void consumeOnDirectQ1(String msg){
        System.out.println("------------------------Consumer Received Message------------------------ ");
        System.out.println( msg + " ---from Direct Q 1---");
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_Q1)
    public void consumeOnFanoutQ1(String msg){
        System.out.println("------------------------Consumer Received Message------------------------ ");
        System.out.println( msg + " ---from FANOUT_Q1---");
    }
    
    @RabbitListener(queues = RabbitMQConfig.FANOUT_Q2)
    public void consumeOnFanoutQ2(String msg){
        System.out.println("------------------------Consumer Received Message------------------------ ");
        System.out.println( msg + " ---from FANOUT_Q_____2222222---");
    }
    
}
