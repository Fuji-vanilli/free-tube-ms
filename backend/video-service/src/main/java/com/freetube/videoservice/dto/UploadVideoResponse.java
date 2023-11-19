package com.freetube.videoservice.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UploadVideoResponse {
    private String videoId;
    private String videoUrl;
}
