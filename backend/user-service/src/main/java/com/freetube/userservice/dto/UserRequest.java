package com.freetube.userservice.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
}
