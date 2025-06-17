package com.zensar.user_management;

import com.zensar.user_management.controller.UserController;
import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@WebFluxTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void testInsertUser() {
        UserDTO userDTO = new UserDTO(1, "John", "Delhi");

        when(userService.insertUser(userDTO))
                .thenReturn(Mono.just(ResponseEntity.status(CREATED).body(userDTO)));

        webTestClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserDTO.class)
                .isEqualTo(userDTO);
    }

    @Test
    void testGetAllUsers() {
        UserDTO user1 = new UserDTO(1, "John", "Delhi");
        UserDTO user2 = new UserDTO(2, "Jane", "Mumbai");

        when(userService.getAllUsers())
                .thenReturn(Mono.just(ResponseEntity.ok(Flux.fromIterable(Arrays.asList(user1, user2)))));

        webTestClient.get()
                .uri("/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserDTO.class)
                .hasSize(2)
                .contains(user1, user2);
    }

    @Test
    void testGetUserById_found() {
        UserDTO userDTO = new UserDTO(1, "John", "Delhi");

        when(userService.getUserById(1))
                .thenReturn(Mono.just(ResponseEntity.ok(userDTO)));

        webTestClient.get()
                .uri("/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDTO.class)
                .isEqualTo(userDTO);
    }

    @Test
    void testGetUserById_notFound() {
        when(userService.getUserById(99))
                .thenReturn(Mono.just(ResponseEntity.notFound().build()));

        webTestClient.get()
                .uri("/99")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateUser() {
        UserDTO input = new UserDTO(null, "Updated", "Hyderabad");
        UserDTO updated = new UserDTO(1, "Updated", "Hyderabad");

        when(userService.updateUser(1, input))
                .thenReturn(Mono.just(ResponseEntity.ok(updated)));

        webTestClient.put()
                .uri("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(input)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDTO.class)
                .isEqualTo(updated);
    }

    @Test
    void testDeleteUser_found() {
        when(userService.deleteUser(1))
                .thenReturn(Mono.just(ResponseEntity.noContent().<Void>build()));

        webTestClient.delete()
                .uri("/1")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteUser_notFound() {
        when(userService.deleteUser(99))
                .thenReturn(Mono.just(ResponseEntity.notFound().<Void>build()));

        webTestClient.delete()
                .uri("/99")
                .exchange()
                .expectStatus().isNotFound();
    }
}
