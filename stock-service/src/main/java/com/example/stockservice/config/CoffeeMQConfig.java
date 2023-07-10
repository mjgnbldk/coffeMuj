package com.example.stockservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeeMQConfig {


    public static final String COFFEE_QUEUE = "coffee-queue";

    public static final String STOCK_QUEUE = "stock-queue";

    public static final String COFFEE_EXCHANGE = "coffee-exchange";

    public static final String COFFEE_ROUTING_KEY = "coffee-routing";

    public static final String STOCK_ROUTING_KEY = "stock-routing";

    @Bean
    public Queue coffeeQueue(){
        return new Queue(COFFEE_QUEUE);
    }

    @Bean
    public Queue stockQueue() {
        return new Queue(STOCK_QUEUE);
    }

    @Bean
    public TopicExchange coffeeExchange() {
        return new TopicExchange(COFFEE_EXCHANGE);
    }

    @Bean
    public Binding coffeeBinding(Queue coffeeQueue, TopicExchange coffeeExchange) {
        return BindingBuilder.bind(coffeeQueue).to(coffeeExchange).with(COFFEE_ROUTING_KEY);
    }

    @Bean
    public Binding stockBinding(Queue stockQueue, TopicExchange coffeeExchange) {
        return BindingBuilder.bind(stockQueue).to(coffeeExchange).with(STOCK_ROUTING_KEY);
    }

    @Bean
    public MessageConverter coffeeMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate coffeeTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(coffeeMessageConverter());
        return rabbitTemplate;
    }
}
