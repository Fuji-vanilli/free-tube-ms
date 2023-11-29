package com.freetube.userservice.services;

import com.freetube.userservice.dto.UserDtoInfo;
import com.freetube.userservice.dto.UserRequest;
import com.freetube.userservice.dto.UserResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {
    String register(String tokenValue);
    UserResponse create(UserRequest request);
    UserResponse get(String id);
    UserRequest all();
    void delete(String id);
}
