package com.example.bike_store_async.producer;
import com.example.bike_store_async.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(Order order) {
        System.out.println("📦 Enviando pedido: " + order.getOrderId());
        rabbitTemplate.convertAndSend("order.exchange", "order.routing.key", order);
    }
}