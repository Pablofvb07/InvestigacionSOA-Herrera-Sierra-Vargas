package com.example.bike_store_async.consumer;

import com.example.bike_store_async.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentWorker {

    private final Random random = new Random();

    @RabbitListener(queues = "order.payment.queue")
    public void processPayment(Order order) {
        System.out.println("💳 Procesando pago del pedido: " + order.getOrderId());

        boolean success = random.nextBoolean(); // 50% de éxito

        if (!success) {
            System.out.println("❌ Error procesando pago del pedido " + order.getOrderId() + " — reintentando...");
            throw new RuntimeException("Fallo en pago");
        }

        System.out.println("✅ Pago aprobado para el pedido " + order.getOrderId());
    }
}
