package com.freetube.userservice.dto;

import com.freetube.userservice.entities.User;
import com.freetube.userservice.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperImpl implements UserMapper{
    @Override
    public User mapToUser(UserRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .build();
    }

    @Override
    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .subscribeToUsers(user.getSubscribeToUsers())
                .dislikesVideos(user.getDislikesVideos())
                .email(user.getEmail())
                .videoHistory(user.getVideoHistory())
                .likesVideos(user.getLikesVideos())
                .subscribers(user.getSubscribers())
                .id(user.getId())
                .build();
    }
}
