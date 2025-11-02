package com.amazon.ecommerce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // exchanges names
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    
    // direct queues names
    public static final String DIRECT_QUEUE1 = "directQ1";
    public static final String DIRECT_QUEUE2 = "directQ2";

    // fanout queue names 
    public static final String FANOUT_Q1 = "fanoutQ1";
    public static final String FANOUT_Q2 = "fanoutQ2";
    
    // topic queue names 
    public static final String ERROR_TOPIC_Q = "topicErrorQ";
    public static final String INFO_TOPIC_Q = "topicInfoQ";
    public static final String ALL_TOPIC_Q = "topicAllQ";

    // routing keys
    public static final String DIRECT_ROUTING_KEY = "directKey";

    // TOPIC routing keys
    public static final String TOPIC_INFO_KEY = "log.info";
    public static final String TOPIC_ERROR_KEY = "log.error";
    public static final String TOPIC_ALL_KEY = "log.*";


    // declare exchange ----------------------------------
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
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
        return new Queue(FANOUT_Q2);
    }

    @Bean
    Queue topicQ1(){
        return new Queue(ERROR_TOPIC_Q);
    }
    @Bean
    Queue topicQ2(){
        return new Queue(INFO_TOPIC_Q);
    }
    @Bean
    Queue topicQ3(){
        return new Queue(ALL_TOPIC_Q);
    }


    // bind exchange with queue--------------------------------- 
    @Bean
    Binding binding(Queue directQ1, DirectExchange directExchange){
        return BindingBuilder.bind(directQ1).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindFanout1(Queue fanoutQ1, FanoutExchange fanoutExchange ){
        return BindingBuilder.bind(fanoutQ1).to(fanoutExchange);
    }
    @Bean
    Binding bindFanout2(Queue fanoutQ2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQ2).to(fanoutExchange);
    }

    @Bean
    Binding bindTopic1(Queue topicQ1, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQ1).to(topicExchange).with(TOPIC_ERROR_KEY);
    }
    @Bean
    Binding bindTopic2(Queue topicQ2, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQ2).to(topicExchange).with(TOPIC_INFO_KEY);
    }
    @Bean
    Binding bindTopic3(Queue topicQ3, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQ3).to(topicExchange).with(TOPIC_ALL_KEY);
    }


}
