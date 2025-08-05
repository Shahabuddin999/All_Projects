package com.feign.feighinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.feign.dto.UserDTO;


@FeignClient(name = "userClient", url = "http://localhost:8080")
public interface UserClient {

    @PutMapping("/users/{id}/update")
    String updateUserById(
        @PathVariable("id") Long userId,
        @RequestParam("sendEmail") boolean sendEmail,
        @RequestBody UserDTO user,
        @RequestHeader("X-Request-ID") String requestId // ✅ SENT HERE
    );
}
