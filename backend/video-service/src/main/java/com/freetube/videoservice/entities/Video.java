package com.freetube.videoservice.entities;

import com.ctc.wstx.shaded.msv_core.grammar.ConcurExp;
import com.freetube.videoservice.enumeration.VideoStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Document(value = "video")
@Builder
public class Video {
    @Id
    private String id;
    private String description;
    private String title;
    private String userId;
    private String videoUrl;
    private AtomicInteger likes= new AtomicInteger(0);
    private AtomicInteger dislikes= new AtomicInteger(0);
    private Set<String> tags;
    private VideoStatus videoStatus;
    private AtomicInteger viewCount= new AtomicInteger(0);
    private Set<Comment> comments= ConcurrentHashMap.newKeySet();
    private String thumbnailUrl;
    private BigDecimal likeCount;
    private BigDecimal dislikeCount;
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void incrementLikes() {
        this.likes.incrementAndGet();
    }
    public void decrementLikes() {
        this.likes.decrementAndGet();
    }
    public void incrementDislikes() {
        this.dislikes.incrementAndGet();
    }
    public void decrementDislikes() {
        this.dislikes.decrementAndGet();
    }
    public void incrementViewCount() {
        this.viewCount.incrementAndGet();
    }
}
