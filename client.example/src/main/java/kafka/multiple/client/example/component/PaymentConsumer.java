package kafka.multiple.client.example.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void listenPayment(String message) {
        System.out.println("Received Payment: " + message);
    }
}
