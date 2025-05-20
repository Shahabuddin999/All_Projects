package kafka.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kafka.example.dto.OrderEvent;

@Service
public class OrderConsumer {

	@KafkaListener(topics = "order-topic", groupId = "order-group", containerFactory = "orderKafkaListenerFactory")
	public void consume(OrderEvent event) {
		System.out.println("Received Order: " + event);
	}
}
