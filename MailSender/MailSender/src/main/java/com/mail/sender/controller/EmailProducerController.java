package com.mail.sender.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.mail.sender.dto.EmailPayload;

@RestController
@RequestMapping("/email")
public class EmailProducerController {

    @Autowired
    private KafkaTemplate<String, EmailPayload> kafkaTemplate;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailPayload payload) {
        kafkaTemplate.send("mail-topic", payload);
        return "Email queued successfully!";
    }
}