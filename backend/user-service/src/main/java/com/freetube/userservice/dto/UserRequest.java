package com.freetube.userservice.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserRequest {
    private String id;
    private String sub;
    private String givenName;
    private String familyName;
    private String name;
    private String picture;
    private String email;
}
