package com.freetube.videoservice.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommentRequest {
    private String text;
    private String authorId;
}
