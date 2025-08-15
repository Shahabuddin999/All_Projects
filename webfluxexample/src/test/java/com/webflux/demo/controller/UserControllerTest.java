package com.webflux.demo.controller;

import com.webflux.demo.dto.Customer;
import com.webflux.demo.entity.Order;
import com.webflux.demo.entity.User;
import com.webflux.demo.entity.UserWithOrders;
import com.webflux.demo.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsersTest() {
        User u1 = new User(1, "A", "a@email.com");
        User u2 = new User(2, "B", "b@email.com");

        Mockito.when(userService.getAllUsers()).thenReturn(Flux.just(u1, u2));

        webTestClient.get()
                .uri("/users")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .hasSize(2)
                .contains(u1, u2);
    }

    @Test
    void getUserByIdTest() {
        User user = new User(1, "A", "a@email.com");

        Mockito.when(userService.getUserById(1))
                .thenReturn(Mono.just(ResponseEntity.ok(user)));

        webTestClient.get().uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(user);
    }

    @Test
    void getUserByIdWithErrorMesageTest() {
        Mockito.when(userService.getUserByIdWithErrorMesage(99))
                .thenReturn(Mono.just(ResponseEntity.status(404).body("User not found")));

        webTestClient.get().uri("/users/witherror/99")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .isEqualTo("User not found");
    }

    @Test
    void createUserTest() {
        User input = new User(null, "New", "new@email.com");
        User saved = new User(10, "New", "new@email.com");

        Mockito.when(userService.createUser(input)).thenReturn(Mono.just(saved));

        webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(input)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(saved);
    }

    @Test
    void updateUserTest() {
        User updated = new User(1, "Updated", "updated@email.com");

        Mockito.when(userService.updateUser(1,updated)).thenReturn(Mono.just(updated));

        webTestClient.put().uri("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(updated);
    }

    @Test
    void updateUserWithResponseEntityTest() {
        User user = new User(1, "A", "a@email.com");

        Mockito.when(userService.updateUserWithResponseEntity(eq(1), any(User.class)))
                .thenReturn(Mono.just(ResponseEntity.ok(user)));

        webTestClient.put().uri("/users/withresponse/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(user);
    }

    @Test
    void deleteUserTest() {
        Mockito.when(userService.deleteUser(1)).thenReturn(Mono.empty());

        webTestClient.delete().uri("/users/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getAllCustomerUsingFluxTest() {
        Customer c1 = new Customer(1, "Cust1", "c1@mail.com");
        Customer c2 = new Customer(2, "Cust2", "c2@mail.com");

        Mockito.when(userService.getAllCustomerUsingFlux()).thenReturn(Flux.just(c1, c2));

        webTestClient.get().uri("/users/stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class)
                .hasSize(2)
                .contains(c1, c2);
    }

    @Test
    void getUserWithOrdersTest() {
        User user = new User(1, "A", "a@mail.com");
        Order order = new Order(101, "Product1", 1);
        UserWithOrders uwo = new UserWithOrders(user, List.of(order));

        Mockito.when(userService.getUserWithOrders(1)).thenReturn(Mono.just(uwo));

        webTestClient.get().uri("/users/order/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserWithOrders.class)
                .isEqualTo(uwo);
    }

    @Test
    void createOrderTest() {
        Order input = new Order(null, "ProductX", 1);
        Order saved = new Order(100, "ProductX", 1);

        Mockito.when(userService.createOrder(input)).thenReturn(Mono.just(saved));

        webTestClient.post().uri("/users/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(input)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .isEqualTo(saved);
    }

    @Test
    void getAllUsersWithOrdersTest() {
        User user = new User(1, "A", "a@email.com");
        Order order = new Order(1, "Product", 1);
        UserWithOrders result = new UserWithOrders(user, List.of(order));

        Mockito.when(userService.getAllUsersWithOrders()).thenReturn(Flux.just(result));

        webTestClient.get().uri("/users/alluserwithorder")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserWithOrders.class)
                .hasSize(1)
                .contains(result);
    }
}
