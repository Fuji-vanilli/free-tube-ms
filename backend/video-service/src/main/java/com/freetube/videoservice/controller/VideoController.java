package com.freetube.videoservice.controller;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.*;
import com.freetube.videoservice.entities.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface VideoController {
    @PostMapping("upload")
    UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file);
    @PatchMapping("thumbnail")
    ResponseEntity<Response> uploadThumbnail(@RequestParam("file") MultipartFile file,
                                             @RequestParam("videoId") String videoId);
    @PutMapping("edit")
    ResponseEntity<Response> editVideo(@RequestBody VideoRequest request);
    @GetMapping("details/{videoId}")
    VideoResponse getVideoDetails(@PathVariable String videoId);
    @PutMapping("saveDetails")
    public ResponseEntity<Response> saveVideoDetails(@RequestBody VideoRequest request);
    @PatchMapping("/{videoId}/like")
    public ResponseEntity<VideoResponse> likeVideo(@PathVariable String videoId);
    @PatchMapping("/{videoId}/dislike")
    public ResponseEntity<VideoResponse> dislikeVideo(@PathVariable String videoId);
    @PatchMapping("comment/{videoId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String videoId, @RequestBody CommentRequest request);
    @GetMapping("comments/{videoId}")
    public ResponseEntity<Set<CommentResponse>> allComments(@PathVariable String videoId);
    @GetMapping("allVideo")
    public ResponseEntity<Set<VideoResponse>> allVideos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    );
}
