package com.example.bike_store_async.consumer;

import com.example.bike_store_async.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailWorker {

    @RabbitListener(queues = "order.email.queue")
    public void sendEmail(Order order) {
        System.out.println("📨 Enviando correo a " + order.getCustomerEmail() +
                " por el pedido " + order.getOrderId());
    }
}
