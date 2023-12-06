package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.*;
import com.freetube.videoservice.entities.Comment;
import com.freetube.videoservice.entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface VideoService {
    UploadVideoResponse uploadVideo(MultipartFile file);
    Response saveVideoDetails(VideoRequest request);
    Response editVideo(VideoRequest videoRequest);
    Response uploadThumbnail(MultipartFile file, String videoId);
    VideoResponse getDetailsVideo(String videoId);
    VideoResponse likeVideo(String videoId);
    VideoResponse dislikeVideo(String videoId);
    void increaseVideoCount(Video video);
    CommentResponse addComment(String videoId, CommentRequest request);
    Set<CommentResponse> allComments(String videoId);
    Set<VideoResponse> allVideos(int page, int size);
}
