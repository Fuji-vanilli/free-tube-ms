package com.freetube.userservice.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freetube.userservice.dto.UserDtoInfo;
import com.freetube.userservice.dto.UserMapper;
import com.freetube.userservice.dto.UserRequest;
import com.freetube.userservice.dto.UserResponse;
import com.freetube.userservice.entities.User;
import com.freetube.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    @Value("${auth.user-info-endpoint}")
    private String userInfoEndpoint;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public String register(String tokenValue) {
        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfoEndpoint))
                .setHeader("Authorization", String.format("Bearer %s", tokenValue))
                .build();

        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        //UserDtoInfo userDtoInfo;
        String body= "";
        try {
            final HttpResponse<String> responseString = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            body = responseString.toString();
            /*ObjectMapper objectMapper= new ObjectMapper();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            userDtoInfo = objectMapper.readValue(body, UserDtoInfo.class);

            User user = userMapper.mapToUser(userDtoInfo);

            System.out.println(body);

            userRepository.save(user);
            log.info("new user register successfully!!!");*/

        } catch (IOException e) {
            throw new RuntimeException("error to read the value!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException("error to send the request!!!");
        }
        return body;
    }

    @Override
    public UserResponse create(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse get(String id) {
        return null;
    }

    @Override
    public UserRequest all() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
