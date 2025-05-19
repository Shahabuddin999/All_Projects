package kafka.multiple.client.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kafka.multiple.client.example.service.OrderProducer;
import kafka.multiple.client.example.service.PaymentProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final OrderProducer orderProducer;
    private final PaymentProducer paymentProducer;

    public KafkaTestController(OrderProducer orderProducer, PaymentProducer paymentProducer) {
        this.orderProducer = orderProducer;
        this.paymentProducer = paymentProducer;
    }

    @PostMapping("/order")
    public ResponseEntity<String> sendOrder(@RequestParam String message) {
        orderProducer.sendOrder(message);
        return ResponseEntity.ok("Order message sent");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> sendPayment(@RequestParam String message) {
        paymentProducer.sendPayment(message);
        return ResponseEntity.ok("Payment message sent");
    }
}
