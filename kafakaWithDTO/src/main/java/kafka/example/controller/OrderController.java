package kafka.example.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import kafka.example.constant.ConstantProperties;
import kafka.example.dto.OrderEvent;
import kafka.example.service.CommittedMessagePoller;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    CommittedMessagePoller committedMessagePoller;
    public OrderController(KafkaTemplate<String, OrderEvent> kafkaTemplate,CommittedMessagePoller committedMessagePoller) {
        this.kafkaTemplate = kafkaTemplate;
        this.committedMessagePoller = committedMessagePoller;
    }

    @PostMapping
    public String publishOrder(@RequestBody OrderEvent orderEvent) { // Commit the message to the topic
        kafkaTemplate.send(ConstantProperties.ORDER_TOPIC, orderEvent);
        return "Order sent to Kafka successfully";
    }
    
    @GetMapping
    public void pollOrder() { // Get commited message
    	committedMessagePoller.pollCommittedMessages();
    }
}
