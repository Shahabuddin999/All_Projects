package kafka.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kafka.example.constant.ConstantProperties;
import kafka.example.dto.OrderEvent;

@Service
public class OrderConsumer {

	@KafkaListener(topics = ConstantProperties.ORDER_TOPIC, groupId = ConstantProperties.ORDER_GROUP, containerFactory = "orderkafkaListenerContainerFactory")
	public void consume(OrderEvent event) {
		int i = 10/event.getQuantity();  // when you send 0 in quality parameter from postman then it will throw the error and it will go to the ORDER_FAILED_TOPIC
		System.out.println("Received Order: " + event);
	}
	
	@KafkaListener(topics = ConstantProperties.ORDER_TOPIC_FAILED, groupId = ConstantProperties.ORDER_GROUP, containerFactory = "orderkafkaListenerContainerFactory")
	public void failledConsumer(OrderEvent event) {
		System.out.println("Failled Order for the consumer: " + event);
	}
}
