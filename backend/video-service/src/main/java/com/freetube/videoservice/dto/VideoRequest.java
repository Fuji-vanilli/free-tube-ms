package com.freetube.videoservice.dto;

import com.freetube.videoservice.enumeration.VideoStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class VideoRequest {
    private String id;
    private String description;
    private String title;
    private Set<String> tags;
    private String videoStatus;
    private String videoUrl;
    private String thumbnailUrl;
}
