package com.freetube.userservice.controller;

import com.freetube.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpRequest;

import static com.freetube.userservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class UserApi implements UserController{
    private final UserService userService;
    @Override
    public String register(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        final String tokenValue = jwt.getTokenValue();


        return null;

    }
}
