package kafka.example.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import kafka.example.constant.ConstantProperties;
import kafka.example.dto.OrderEvent;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, OrderEvent> orderConsumerFactory() {
		JsonDeserializer<OrderEvent> deserializer = new JsonDeserializer<>(OrderEvent.class);
		deserializer.addTrustedPackages("*"); // or specify your package like "kafka.example.dto.OrderEvent"

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, ConstantProperties.ORDER_GROUP);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> orderkafkaListenerContainerFactory(
	        KafkaTemplate<String, OrderEvent> kafkaTemplate) {

	    ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory =
	            new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(orderConsumerFactory());

	    // Dead-letter recoverer: sends failed messages to DLQ
	    DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
	            (record, ex) -> {
	                Throwable rootCause = ex instanceof org.springframework.kafka.listener.ListenerExecutionFailedException
	                        ? ex.getCause()
	                        : ex;

	                StackTraceElement location = rootCause.getStackTrace()[0]; // first element
	                System.err.println("Sending failed message to Topic: " + ConstantProperties.ORDER_TOPIC_FAILED);
	                System.err.println("Exception: " + rootCause);
	                System.err.println("Location: " + location.getClassName() + "." + location.getMethodName() +
	                        " (line: " + location.getLineNumber() + ")");
	                return new TopicPartition(ConstantProperties.ORDER_TOPIC_FAILED, record.partition());
	            });

	    // Retry: 3 attempts with 2 seconds interval
	    FixedBackOff backOff = new FixedBackOff(2000L, 1);

	    DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);

	    // Retry listener: logs exception, topic, value, location
	    errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
	        Throwable rootCause = ex instanceof org.springframework.kafka.listener.ListenerExecutionFailedException
	                ? ex.getCause()
	                : ex;

	        StackTraceElement location = rootCause.getStackTrace()[0]; // first element

	        System.err.println("Retry attempt " + deliveryAttempt + " for message from topic: "
	                + record.topic() + ", value: " + record.value());
	        System.err.println("Exception: " + rootCause);
	        System.err.println("Location: " + location.getClassName() + "." + location.getMethodName() +
	                " (line: " + location.getLineNumber() + ")");
	    });

	    factory.setCommonErrorHandler(errorHandler);

	    return factory;
	}


}
