package com.amazon.ecommerce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    private static final String QUEUE_NAME = "myQueue";
    private static final String EXCHANGE_NAME = "myExchange";
    private static final String ROUTING_KEY = "myRoutingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

}
