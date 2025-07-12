package com.mail.sender.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mail.sender.dto.EmailPayload;
import com.mail.sender.service.MailService;

@Component
public class EmailConsumer {

    @Autowired
    private MailService mailService;

    @KafkaListener(topics = "mail-topic", groupId = "mail-group")
    public void consume(EmailPayload email) {
        mailService.send(email.getTo(), email.getSubject(), email.getBody());
    }
}