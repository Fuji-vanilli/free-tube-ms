package com.freetube.videoservice.controller;

import com.freetube.videoservice.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {
    @GetMapping("register")
    UserResponse register(Authentication authentication);


}
