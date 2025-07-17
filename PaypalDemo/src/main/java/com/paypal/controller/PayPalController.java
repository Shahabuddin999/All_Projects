package com.paypal.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paypal.dto.AmountDto;
import com.paypal.orders.Order;
import com.paypal.service.PayPalService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {
	@Autowired
    PayPalService payPalService;

    @PostMapping("/order")
    public Map<String, String> createOrder(@RequestBody AmountDto dto, HttpServletRequest request) throws IOException {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
        String orderId = payPalService.createOrder(dto.getAmount(), dto.getCurrency(), baseUrl + "/success", baseUrl + "/cancel");
        return Map.of("orderId", orderId);
    }

    @PostMapping("/capture/{orderId}")
    public Order capture(@PathVariable String orderId) throws IOException {
        return payPalService.captureOrder(orderId);
    }
}
