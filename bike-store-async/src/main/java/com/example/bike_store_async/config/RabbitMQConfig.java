package com.example.bike_store_async.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "order.exchange";
    public static final String QUEUE_PAYMENT = "order.payment.queue";
    public static final String QUEUE_EMAIL = "order.email.queue";
    public static final String DLQ = "order.dead.queue";
    public static final String ROUTING_KEY = "order.routing.key";

    // Exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    // Cola de pagos
    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable(QUEUE_PAYMENT)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DLQ)
                .build();
    }

    // Cola de emails
    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_EMAIL, true);
    }

    // Dead Letter Queue
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ, true);
    }

    // Binding para pagos
    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(paymentQueue()).to(exchange()).with(ROUTING_KEY);
    }

    // Binding para emails
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(exchange()).with(ROUTING_KEY);
    }

    // RabbitTemplate con conversor JSON
    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    // Conversor para listeners
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
