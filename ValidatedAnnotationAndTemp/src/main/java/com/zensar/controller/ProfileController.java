package com.zensar.controller;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zensar.nextdto.Order;
import com.zensar.nextdto.Payment;
import com.zensar.nextdto.User;
import com.zensar.nextdto.UserProfileResponse;
import com.zensar.service.OrderService;
import com.zensar.service.PaymentService;
import com.zensar.service.UserService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public ProfileController(UserService userService, OrderService orderService, PaymentService paymentService) {
        this.userService = userService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

//    @GetMapping("/{userId}")
//    public CompletableFuture<UserProfileResponse> getProfile(@PathVariable String userId) {
//
//        CompletableFuture<User> userFuture = userService.getUser(userId);
//        CompletableFuture<List<Order>> ordersFuture = orderService.getOrders(userId);
//        CompletableFuture<List<Payment>> paymentsFuture = paymentService.getPayments(userId);
//
//        return CompletableFuture.allOf(userFuture, ordersFuture, paymentsFuture)
//                .thenApply(v -> new UserProfileResponse(
//                        userFuture.join(),
//                        ordersFuture.join(),
//                        paymentsFuture.join()
//                ));
//    }
    
    @GetMapping("/{userId}")
    public CompletableFuture<ResponseEntity<UserProfileResponse>> getProfile(@PathVariable String userId) {

        CompletableFuture<User> userFuture = userService.getUser(userId);
        CompletableFuture<List<Order>> ordersFuture = orderService.getOrders(userId);
        CompletableFuture<List<Payment>> paymentsFuture = paymentService.getPayments(userId);

        return CompletableFuture.allOf(userFuture, ordersFuture, paymentsFuture)
                .thenApply(v -> {
                    UserProfileResponse response = new UserProfileResponse(
                            userFuture.join(),
                            ordersFuture.join(),
                            paymentsFuture.join()
                    );
                    return new ResponseEntity<>(response, HttpStatus.FOUND);
                });
    }

}
