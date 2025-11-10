package com.example.bike_store_async.controller;

import com.example.bike_store_async.model.Order;
import com.example.bike_store_async.producer.OrderProducer;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        producer.sendOrder(order);
        return "Pedido enviado con ID: " + order.getOrderId();
    }
}
