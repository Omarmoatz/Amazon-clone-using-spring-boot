package com.amazon.ecommerce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // exchanges names
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    
    // direct queues names
    public static final String DIRECT_QUEUE1 = "directQ1";
    public static final String DIRECT_QUEUE2 = "directQ2";

    // fanout queue names 
    public static final String FANOUT_Q1 = "fanoutQ1";
    public static final String FANOUT_Q2 = "fanoutQ2";

    // routing keys
    public static final String DIRECT_ROUTING_KEY = "directKey";


    // declare exchange ----------------------------------
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    // declare queues ------------------------------
    @Bean
    Queue directQ1() {
        return new Queue(DIRECT_QUEUE1, true);
    }
    
    @Bean
    Queue directQ2() {
        return new Queue(DIRECT_QUEUE2, true);
    }

    @Bean
    Queue fanoutQ1(){
        return new Queue(FANOUT_Q1);
    }
    
    @Bean
    Queue fanoutQ2(){
        return new Queue(FANOUT_Q1);
    }


    // bind exchange with queue--------------------------------- 
    @Bean
    Binding binding(Queue directQueue, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindFanout1(Queue fanoutQ1, FanoutExchange fanoutExchange ){
        return BindingBuilder.bind(fanoutQ1).to(fanoutExchange);
    }
    
    @Bean
    Binding bindFanout2(Queue fanoutQ2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQ2).to(fanoutExchange);
    }



}
