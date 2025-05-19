package kafka.multiple.client.example.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @KafkaListener(topics = "order-topic", groupId = "order-group", containerFactory = "orderKafkaListenerFactory")
    public void listenOrder(String message) {
        System.out.println("Received Order: " + message);
    }
}

