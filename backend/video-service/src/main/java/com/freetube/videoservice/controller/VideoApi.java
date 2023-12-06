package com.freetube.videoservice.controller;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.*;
import com.freetube.videoservice.entities.Comment;
import com.freetube.videoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

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
    public ResponseEntity<VideoResponse> likeVideo(String videoId) {
        return ResponseEntity.ok(videoService.likeVideo(videoId));
    }

    @Override
    public ResponseEntity<VideoResponse> dislikeVideo(String videoId) {
        return ResponseEntity.ok(videoService.dislikeVideo(videoId));
    }

    @Override
    public ResponseEntity<CommentResponse> addComment(String videoId, CommentRequest request) {
        return ResponseEntity.ok(videoService.addComment(videoId, request));
    }

    @Override
    public ResponseEntity<Set<CommentResponse>> allComments(String videoId) {
        return ResponseEntity.ok(videoService.allComments(videoId));
    }

    @Override
    public ResponseEntity<Set<VideoResponse>> allVideos(int page, int size) {
        return ResponseEntity.ok(videoService.allVideos(page, size));
    }
}
