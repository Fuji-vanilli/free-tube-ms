package com.freetube.videoservice.controller;

import com.freetube.videoservice.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface UserController {
    @GetMapping("register")
    UserResponse register(Authentication authentication);
    @GetMapping("subscribe/{userId}")
    UserResponse subscribeToUser(@PathVariable String userId);
    @GetMapping("unSubscribe/{userId}")
    UserResponse unSubscribeToUser(@PathVariable String userId);
    @GetMapping("userHistories/{userId}")
    ResponseEntity<Set<String>> getUserHistories(@PathVariable String userId);

}
