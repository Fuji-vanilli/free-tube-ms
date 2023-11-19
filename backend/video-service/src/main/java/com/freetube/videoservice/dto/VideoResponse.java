package com.freetube.videoservice.dto;

import com.freetube.videoservice.enumeration.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class VideoResponse {
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
