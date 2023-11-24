package com.freetube.userservice.controller;

import org.springframework.security.core.Authentication;

public interface UserController {

    String register(Authentication authentication);
}
