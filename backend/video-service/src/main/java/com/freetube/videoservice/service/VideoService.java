package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UploadVideoResponse;
import com.freetube.videoservice.dto.VideoRequest;
import com.freetube.videoservice.dto.VideoResponse;
import com.freetube.videoservice.entities.Video;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    UploadVideoResponse uploadVideo(MultipartFile file);
    Response saveVideoDetails(VideoRequest request);
    Response editVideo(VideoRequest videoRequest);
    Response uploadThumbnail(MultipartFile file, String videoId);
    VideoResponse getDetailsVideo(String videoId);
    Response likeVideo(String videoId);
    Response dislikeVideo(String videoId);
    void increaseVideoCount(Video video);
}
