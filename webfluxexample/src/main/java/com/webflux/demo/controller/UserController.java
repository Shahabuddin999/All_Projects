package com.webflux.demo.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.demo.dto.Customer;
import com.webflux.demo.entity.Order;
import com.webflux.demo.entity.User;
import com.webflux.demo.entity.UserWithOrders;
import com.webflux.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("witherror/{id}")
    public Mono<ResponseEntity<Object>> getUserByIdWithErrorMesage(@PathVariable("id") Integer id) {
        return userService.getUserByIdWithErrorMesage(id);
    }


    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
    @PutMapping("/withresponse/{id}")
    public Mono<ResponseEntity<User>> updateUserWithResponseEntity(@PathVariable("id") Integer id, @RequestBody User user) {
        return userService.updateUserWithResponseEntity(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
    
    // "produces = MediaType.TEXT_EVENT_STREAM_VALUE" is important to work with Flux as it get record and will return else will not work
    @GetMapping(value="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) 
    public Flux<Customer> getAllCustomerUsingFlux() {
        return userService.getAllCustomerUsingFlux();
    }
    
    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Mono<UserWithOrders> getUserWithOrders(@PathVariable("id")Integer id) {
        return userService.getUserWithOrders(id);
    }
    
    @PostMapping(value="/order")
    public Mono<Order> createOrder(@RequestBody Order user) {
        return userService.createOrder(user);
    }
    
    @GetMapping(value="/alluserwithorder", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserWithOrders> getAllUsersWithOrders(){
    	return userService.getAllUsersWithOrders();
    }
}
