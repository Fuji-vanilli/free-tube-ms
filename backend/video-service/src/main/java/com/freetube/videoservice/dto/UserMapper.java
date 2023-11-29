package com.freetube.videoservice.dto;

import com.freetube.videoservice.entities.User;

public interface UserMapper {
    User mapToUser(UserDtoInfo request);
    UserResponse mapToUserResponse(User user);
}
