package com.freetube.videoservice.dto;

import com.freetube.videoservice.entities.Video;

public interface VideoMapper {
    Video mapToVideo(VideoRequest request);
    VideoResponse mapToVideoResponse(Video video);
}
