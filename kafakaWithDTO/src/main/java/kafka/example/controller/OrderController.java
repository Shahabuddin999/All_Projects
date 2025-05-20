package kafka.example.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import kafka.example.dto.OrderEvent;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String TOPIC = "order-topic";

    public OrderController(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String publishOrder(@RequestBody OrderEvent orderEvent) {
        kafkaTemplate.send(TOPIC, orderEvent);
        return "Order sent to Kafka successfully";
    }
}
