package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var orderDispatcher = new KafkaDispatcher<Order>()) {
            try (var emailDispatcher = new KafkaDispatcher<String>()) {
                // pedidoId, ClientId, ValorCompra
        
                // Com a key aleatório será um id do usuário diferente a cada vez que rodar
                var userId = UUID.randomUUID().toString();
                var orderId = UUID.randomUUID().toString();
                var amount = new BigDecimal(Math.random() * 5000 + 1);

                var order = new Order(userId, orderId, amount);
                orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
        
                var email = "Thank you for your order! We are processing your order!";
                emailDispatcher.send("ECOMMERCER_SEND_EMAIL", userId, email);
            }
        }
    }

}
