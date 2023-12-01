package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UserResponse;
import com.freetube.videoservice.entities.User;

public interface UserService {
    UserResponse register(String tokenValue);
    User getCurrentUser();
    Response addToLikedVideos(String videoId);
    Response removeFromLikedVideos(String videoId);
    Response addToDislikedVideos(String videoId);
    Response removeFromDislikedVideos(String videoId);
    boolean isLikedVideo(String videoId);
    boolean isDislikedVideo(String videoId);
    void addVideoToHistory(String videoId);
}
