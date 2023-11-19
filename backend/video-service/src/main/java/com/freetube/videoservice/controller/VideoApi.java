package com.freetube.videoservice.controller;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UploadVideoResponse;
import com.freetube.videoservice.dto.VideoRequest;
import com.freetube.videoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.freetube.videoservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
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
}
