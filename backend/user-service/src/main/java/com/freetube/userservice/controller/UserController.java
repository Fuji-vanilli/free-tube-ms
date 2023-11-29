package com.freetube.userservice.controller;

import com.freetube.userservice.dto.UserDtoInfo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {

    @GetMapping("register")
    String register(Authentication authentication);
}
