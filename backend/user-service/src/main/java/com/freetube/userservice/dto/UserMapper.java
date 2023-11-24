package com.freetube.userservice.dto;

import com.freetube.userservice.entities.User;

public interface UserMapper {
    User mapToUser(UserRequest request);
    UserResponse mapToUserResponse(User user);
}
