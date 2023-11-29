package com.freetube.userservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

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
    private Set<String> likesVideos;
    private Set<String> dislikesVideos;
}
