package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UserResponse;
import com.freetube.videoservice.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponse register(String tokenValue);
    UserResponse subscribeToUser(String userId);
    UserResponse unSubscribeToUser(String userId);
    User getCurrentUser();
    Response addToLikedVideos(String videoId);
    Response removeFromLikedVideos(String videoId);
    Response addToDislikedVideos(String videoId);
    Response removeFromDislikedVideos(String videoId);
    boolean isLikedVideo(String videoId);
    boolean isDislikedVideo(String videoId);
    void addVideoToHistory(String videoId);
    Set<String> getUserHistories(String userId);
}
