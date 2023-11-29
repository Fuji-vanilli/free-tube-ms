package com.freetube.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDtoInfo {
    private String id;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("nickname")
    private String familyName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("email")
    private String email;
}
