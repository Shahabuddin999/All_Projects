package com.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.constant.ConstantProperties;
import com.kafka.service.CommittedMessagePoller;
import com.kafka.service.KafkaProducerService;


@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    private final KafkaProducerService producerService;

    private final CommittedMessagePoller committedMessagePoller;
    
    public KafkaProducerController(KafkaProducerService producerService, CommittedMessagePoller committedMessagePoller) {
        this.producerService = producerService;
        this.committedMessagePoller = committedMessagePoller;
    }

    @PostMapping
    public String send(@RequestParam String message) {
        producerService.sendMessage(ConstantProperties.ORDER_TOPIC, message);
        return "Sent: " + message;
    }
    
    @GetMapping
    public void pollOrder() { // Get commited message
    	committedMessagePoller.pollCommittedMessages();
    }
}
