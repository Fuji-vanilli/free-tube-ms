package com.freetube.videoservice.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String sub;
    private Set<String> subscribeToUsers;
    private Set<String> subscribers;
    private Set<String> videoHistory;
    private Set<String> likesVideos;
    private Set<String> dislikesVideos;
}
