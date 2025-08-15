package com.mail.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    
    private static final String FROM_EMAIL = "ansari.shahabuddin999@gmail.com"; // ✅ Verified SES email

    // Here I m using spring.mail.host=smtp.gmail.com its slower, to make more scalable and faster use "SendGrid" or "Amazon SES" which are more faster and scalable and can handle million billion mail in a second but SMTP can't. See Application.properties file
    @Async
    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL); 
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
