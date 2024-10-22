package br.com.alura.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher()) {
            // pedidoId, ClientId, ValorCompra
    
            // Com a key aleatório será um id do usuário diferente a cada vez que rodar
            var key = UUID.randomUUID().toString();
            var value = "123132132,4566219,23,00";
            dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
    
            var email = "Thank you for your order! We are processing your order!";
            dispatcher.send("ECOMMERCER_SEND_EMAIL", key, email);
        }
    }

}
