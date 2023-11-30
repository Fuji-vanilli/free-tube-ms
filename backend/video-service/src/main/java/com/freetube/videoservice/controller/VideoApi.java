package com.freetube.videoservice.controller;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UploadVideoResponse;
import com.freetube.videoservice.dto.VideoRequest;
import com.freetube.videoservice.dto.VideoResponse;
import com.freetube.videoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.freetube.videoservice.Utils.Root.APP_ROOT_VIDEO;

@RestController
@RequestMapping(APP_ROOT_VIDEO)
@RequiredArgsConstructor
public class VideoApi implements VideoController {
    private final VideoService videoService;
    @Override
    public UploadVideoResponse uploadVideo(MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @Override
    public ResponseEntity<Response> uploadThumbnail(MultipartFile file, String videoId) {
        return ResponseEntity.ok(videoService.uploadThumbnail(file, videoId));
    }

    @Override
    public ResponseEntity<Response> editVideo(VideoRequest request) {
        return ResponseEntity.ok(videoService.editVideo(request));
    }

    @Override
    public VideoResponse getVideoDetails(String videoId) {
        return videoService.getDetailsVideo(videoId);
    }

    @Override
    public ResponseEntity<Response> saveVideoDetails(VideoRequest request) {
        return ResponseEntity.ok(videoService.saveVideoDetails(request));
    }

    @Override
    public ResponseEntity<Response> likeVideo(String videoId) {
        return ResponseEntity.ok(videoService.likeVideo(videoId));
    }

    @Override
    public ResponseEntity<Response> dislikeVideo(String videoId) {
        return ResponseEntity.ok(videoService.dislikeVideo(videoId));
    }
}
