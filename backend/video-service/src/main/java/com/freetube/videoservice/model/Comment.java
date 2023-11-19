package com.freetube.videoservice.model;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Comment {
    private String id;
    private String authorId;
    private String text;
    private BigDecimal likeCount;
    private BigDecimal dislikeCount;

}
