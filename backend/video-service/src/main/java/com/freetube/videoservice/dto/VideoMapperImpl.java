package com.freetube.videoservice.dto;

import com.freetube.videoservice.entities.Video;
import com.freetube.videoservice.enumeration.VideoStatus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoMapperImpl implements VideoMapper {
    @Override
    public Video mapToVideo(VideoRequest request) {
        return Video.builder()
                .videoStatus(VideoStatus.valueOf(request.getVideoStatus().toUpperCase()))
                .title(request.getTitle())
                .tags(request.getTags())
                .thumbnailUrl(request.getThumbnailUrl())
                .description(request.getDescription())
                .build();
    }

    @Override
    public VideoResponse mapToVideoResponse(Video video) {
        return VideoResponse.builder()
                .id(video.getId())
                .description(video.getDescription())
                .videoUrl(video.getVideoUrl())
                .likes(video.getLikes())
                .dislikes(video.getDislikes())
                .tags(video.getTags())
                .thumbnailUrl(video.getThumbnailUrl())
                .title(video.getTitle())
                .userId(video.getUserId())
                .viewCount(video.getViewCount())
                .videoStatus(video.getVideoStatus())
                .likeCount(video.getLikeCount())
                .dislikeCount(video.getDislikeCount())
                .build();
    }
}
