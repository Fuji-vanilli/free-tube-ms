package com.freetube.userservice.dto;

import com.freetube.userservice.entities.User;

public interface UserMapper {
    User mapToUser(UserDtoInfo request);
    UserResponse mapToUserResponse(User user);
}
