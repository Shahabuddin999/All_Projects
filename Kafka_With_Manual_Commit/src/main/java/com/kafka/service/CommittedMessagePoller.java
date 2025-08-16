package com.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.springframework.stereotype.Service;

import com.kafka.constant.ConstantProperties;

import java.time.Duration;
import java.util.*;

@Service
public class CommittedMessagePoller {

    private final KafkaConsumer<String, String> consumer;

    public CommittedMessagePoller(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }
    
    public void pollCommittedMessages() {
        // 1. Subscribe to topic
    	String topic = ConstantProperties.ORDER_TOPIC;
        consumer.subscribe(Collections.singletonList(topic));

        // 2. Poll once to get assignment
        consumer.poll(Duration.ofMillis(1000));
        Set<TopicPartition> partitions = consumer.assignment();

        // 3. Seek to last committed offsets
        for (TopicPartition partition : partitions) {
            OffsetAndMetadata committed = consumer.committed(partition);
            long offset = (committed != null) ? committed.offset() : 0L;
            consumer.seek(partition, offset);
        }

        System.out.println("Polling committed messages...");

        // 4. Poll messages
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("Committed Message: " + record.value() +
                    " | Partition: " + record.partition() +
                    " | Offset: " + record.offset());
        }
    }
}
