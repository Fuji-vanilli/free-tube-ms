package com.freetube.videoservice.entities;

import com.freetube.videoservice.enumeration.VideoStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    private BigDecimal likes;
    private BigDecimal dislikes;
    private Set<String> tags;
    private VideoStatus videoStatus;
    private BigDecimal viewCount;
    private String thumbnailUrl;

}
