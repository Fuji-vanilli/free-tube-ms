package com.freetube.videoservice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UserDtoInfo;
import com.freetube.videoservice.dto.UserMapper;
import com.freetube.videoservice.dto.UserResponse;
import com.freetube.videoservice.entities.User;
import com.freetube.videoservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    @Value("${auth0.user-info-endpoint}")
    private String userInfoEndpoint;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponse register(String tokenValue) {
        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfoEndpoint))
                .setHeader("Authorization", String.format("Bearer %s", tokenValue))
                .build();

        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        String body= "";
        UserDtoInfo userDtoInfo;
        try {
            final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            body= response.body();

            ObjectMapper objectMapper= new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            userDtoInfo = objectMapper.readValue(body, UserDtoInfo.class);

        } catch (IOException e) {
            throw new RuntimeException("error to map the user dto info");
        } catch (InterruptedException e) {
            throw new RuntimeException("error to send the request");
        }


        final User user = userMapper.mapToUser(userDtoInfo);
        userRepository.save(user);

        log.info("new user register successfully!!!");

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse subscribeToUser(String userId) {
        User currentUser= getCurrentUser();
        User user= userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find user with the id: "+userId));

        currentUser.addSubscribeToUsers(userId);
        userRepository.save(currentUser);
        log.info("current user subscribed to user: {} successfully!", userId);

        user.addSubscribers(currentUser.getId());
        userRepository.save(user);
        log.info("user: {} subscribed successfully!", currentUser.getId());

        return userMapper.mapToUserResponse(currentUser);
    }

    @Override
    public UserResponse unSubscribeToUser(String userId) {
        User currentUser= getCurrentUser();
        User user= userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find user with the id: "+userId));

        currentUser.removeFromSubscribeToUsers(userId);
        userRepository.save(currentUser);
        log.info("current user unSubscribed to user: {} successfully!", userId);

        user.removeFromSubscribers(currentUser.getId());
        userRepository.save(user);
        log.info("user: {} unSubscribed successfully!", currentUser.getId());

        return userMapper.mapToUserResponse(currentUser);
    }

    @Override
    public User getCurrentUser() {
        //final String sub = ((Jwt) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");

        final Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String sub = principal.getClaim("sub");

        return userRepository.findBySub(sub)
                .orElseThrow(()-> new IllegalArgumentException("Can't find user with the sub: "+sub+" into the database!"));
    }

    @Override
    public Response addToLikedVideos(String videoId) {
        User currentUser= getCurrentUser();

        currentUser.addToLikeVideos(videoId);

        userRepository.save(currentUser);
        log.info("video: {} liked added to the user: {}", videoId, currentUser.getId());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "user", userMapper.mapToUserResponse(currentUser)
                ),
                "video: "+videoId+" liked added to the user: "+currentUser.getId()
        );
    }

    @Override
    public Response removeFromLikedVideos(String videoId) {
        User currentUser= getCurrentUser();
        currentUser.removeFromLikedVideos(videoId);

        userRepository.save(currentUser);
        log.info("video: {} liked removed from the user: {}", videoId, currentUser.getId());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "user", userMapper.mapToUserResponse(currentUser)
                ),
                "video: "+videoId+" liked removed from the user: "+currentUser.getId()
        );
    }

    @Override
    public Response addToDislikedVideos(String videoId) {
        User currentUser= getCurrentUser();

        currentUser.addToDislikeVideos(videoId);

        userRepository.save(currentUser);
        log.info("video: {} disliked added to the user: {}", videoId, currentUser.getId());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "user", userMapper.mapToUserResponse(currentUser)
                ),
                "video: "+videoId+" disliked added to the user: "+currentUser.getId()
        );
    }

    @Override
    public Response removeFromDislikedVideos(String videoId) {
        User currentUser= getCurrentUser();

        currentUser.removeFromDislikedVideos(videoId);

        userRepository.save(currentUser);
        log.info("video: {} disliked removed to the user: {}", videoId, currentUser.getId());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "user", userMapper.mapToUserResponse(currentUser)
                ),
                "video: "+videoId+" disliked removed to the user: "+currentUser.getId()
        );
    }

    @Override
    public boolean isLikedVideo(String videoId) {

        return getCurrentUser().getLikesVideos().stream()
                .anyMatch(likedVideo-> likedVideo.equals(videoId));
    }

    @Override
    public boolean isDislikedVideo(String videoId) {
        return getCurrentUser().getDislikesVideos().stream()
                .anyMatch(dislikedVideo-> dislikedVideo.equals(videoId));
    }

    @Override
    public void addVideoToHistory(String videoId) {
        User user= getCurrentUser();
        user.addToVideoHistory(videoId);
        userRepository.save(user);
    }

    @Override
    public Set<String> getUserHistories(String userId) {
        User user= getUseById(userId);

        return user.getVideoHistory();
    }

    @Override
    public UserResponse getUser(String userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find the user with the id: "+userId));

        return userMapper.mapToUserResponse(user);
    }

    private User getUseById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find user with the id: "+userId));
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }
}
