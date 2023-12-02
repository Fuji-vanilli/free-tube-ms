package com.freetube.videoservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "comment")
public class Comment {
    @Id
    private String id;
    private String text;
    private String videoId;
    private String authorId;
    private AtomicInteger likeCount= new AtomicInteger(0);
    private AtomicInteger dislikeCount= new AtomicInteger(0);
    public void incrementLikeCount(String userId) {
        likeCount.incrementAndGet();
    }
    public void decrementLikeCount(String userId) {
        likeCount.decrementAndGet();
    }
    public void incrementDislikeCount(String userId) {
        dislikeCount.incrementAndGet();
    }
    public void decrementDislikeCount(String userId) {
        dislikeCount.decrementAndGet();
    }
}
