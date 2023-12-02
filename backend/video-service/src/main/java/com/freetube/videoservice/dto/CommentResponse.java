package com.freetube.videoservice.dto;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommentResponse {
    private String id;
    private String text;
    private String authorId;
    private String videoId;
    private AtomicInteger likeCount;
    private AtomicInteger dislikeCount;
}
