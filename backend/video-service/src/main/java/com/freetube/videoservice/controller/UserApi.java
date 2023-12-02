package com.freetube.videoservice.controller;

import com.freetube.videoservice.dto.UserResponse;
import com.freetube.videoservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.freetube.videoservice.Utils.Root.APP_ROOT_USER;

@RestController
@RequestMapping(APP_ROOT_USER)
@RequiredArgsConstructor
public class UserApi implements UserController {
    private final UserService userService;
    @Override
    public UserResponse register(Authentication authentication) {
        final Jwt jwt= (Jwt) authentication.getPrincipal();

        final String tokenValue = jwt.getTokenValue();

        return userService.register(tokenValue);
    }

    @Override
    public UserResponse subscribeToUser(String userId) {
        return userService.subscribeToUser(userId);
    }

    @Override
    public UserResponse unSubscribeToUser(String userId) {
        return userService.unSubscribeToUser(userId);
    }
}
