package kafka.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kafka.example.dto.OrderEvent;

@Service
public class OrderConsumer {

	@KafkaListener(topics = "order-topic", groupId = "order-group", containerFactory = "orderKafkaListenerFactory")
	public void consume(OrderEvent event) {
		//int i = 10/0;  // when you enable it retry mechanism will be called defined in KafkaConsumerConfig and this below listener will be called ""order-topic-failed"
		System.out.println("Received Order: " + event);
	}
	
	@KafkaListener(topics = "order-topic-failed", groupId = "order-group", containerFactory = "orderKafkaListenerFactory")
	public void failledConsumer(OrderEvent event) {
		System.out.println("Failled Order for the consumer: " + event);
	}
}
