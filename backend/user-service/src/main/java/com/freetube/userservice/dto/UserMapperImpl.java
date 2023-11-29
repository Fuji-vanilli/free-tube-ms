package com.freetube.userservice.dto;

import com.freetube.userservice.entities.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperImpl implements UserMapper{
    @Override
    public User mapToUser(UserDtoInfo request) {
        return User.builder()
                .email(request.getEmail())
                .sub(request.getSub())
                .picture(request.getPicture())
                .firstname(request.getFamilyName())
                .lastname(request.getName())
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
