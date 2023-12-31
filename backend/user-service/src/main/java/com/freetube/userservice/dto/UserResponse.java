package com.freetube.userservice.dto;

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
    private Set<String> subscribeToUsers;
    private Set<String> subscribers;
    private List<String> videoHistory;
    private Set<String> likesVideos;
    private Set<String> dislikesVideos;
}
