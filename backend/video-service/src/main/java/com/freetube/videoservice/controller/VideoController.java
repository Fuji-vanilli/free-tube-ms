package com.freetube.videoservice.controller;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UploadVideoResponse;
import com.freetube.videoservice.dto.VideoRequest;
import com.freetube.videoservice.dto.VideoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
