package com.freetube.videoservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "user")
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String sub;
    private Set<String> subscribeToUsers;
    private Set<String> subscribers;
    private List<String> videoHistory;
    private Set<String> likesVideos= ConcurrentHashMap.newKeySet();
    private Set<String> dislikesVideos= ConcurrentHashMap.newKeySet();

    public void addToLikeVideos(String videoId) {
        likesVideos.add(videoId);
    }
    public void removeFromLikedVideos(String videoId) {
        likesVideos.remove(videoId);
    }
    public void addToDislikeVideos(String videoId) {
        dislikesVideos.add(videoId);
    }
    public void removeFromDislikedVideos(String videoId) {
        dislikesVideos.remove(videoId);
    }
    public void addToVideoHistory(String videoId) {
        videoHistory.add(videoId);
    }
}
