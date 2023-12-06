package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.*;
import com.freetube.videoservice.entities.Comment;
import com.freetube.videoservice.entities.User;
import com.freetube.videoservice.entities.Video;
import com.freetube.videoservice.enumeration.VideoStatus;
import com.freetube.videoservice.repository.CommentRepository;
import com.freetube.videoservice.repository.UserRepository;
import com.freetube.videoservice.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VideoServiceImpl implements VideoService{
    private final FileService fileService;
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserService userService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public UploadVideoResponse uploadVideo(MultipartFile file) {
        String videoUrl= fileService.uploadFile(file);

        var video= new Video();
        video.setVideoUrl(videoUrl);
        video.setUserId(getCurrentUser().getId());

        videoRepository.save(video);



        log.info("new video added successfully!");
        return UploadVideoResponse.builder()
                .videoId(video.getId())
                .videoUrl(videoUrl)
                .build();
    }

    @Override
    public Response saveVideoDetails(VideoRequest request) {
        String videoId= request.getId();
        Optional<Video> videoOptional= videoRepository.findById(videoId);
        if (videoOptional.isEmpty()) {
            log.error("video with the id: {} doesn't exist on the database", videoId);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "video with the id: "+videoId+" doesn't exist on the database"
            );
        }

        Video video= videoOptional.get();
        video.setTitle(request.getTitle());
        video.setVideoStatus(VideoStatus.valueOf(request.getVideoStatus().toUpperCase()));
        video.setTags(request.getTags());
        video.setDescription(request.getDescription());
        video.setThumbnailUrl(request.getThumbnailUrl());

        videoRepository.save(video);
        log.info("video with the id: {} updated successfully!", videoId);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "video", videoMapper.mapToVideoResponse(video)
                ),
                "video with the id: "+videoId+" updated successfully!"
        );
    }

    @Override
    public Response editVideo(VideoRequest videoRequest) {
        Optional<Video> videoOptional= videoRepository.findById(videoRequest.getId());
        if (videoOptional.isEmpty()) {
            log.error("video with the id: {} doesn't exist on the database", videoRequest.getId());
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "video with the id: "+videoRequest.getId()+" doesn't exist on the database"
            );
        }

        Video video= videoOptional.get();
        video.setTitle(videoRequest.getTitle());
        video.setVideoStatus(VideoStatus.valueOf(videoRequest.getVideoStatus()));
        video.setTags(videoRequest.getTags());
        video.setDescription(videoRequest.getDescription());
        video.setThumbnailUrl(videoRequest.getThumbnailUrl());

        log.info("video with the id: {} edited successfully!", video.getId());

        videoRepository.save(video);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "video", videoMapper.mapToVideoResponse(video)
                ),
                "video with the id: "+video.getId()+" edited successfully!"
        );
    }

    @Override
    public Response uploadThumbnail(MultipartFile file, String videoId) {
        Optional<Video> videoOptional= videoRepository.findById(videoId);
        if (videoOptional.isEmpty()) {
            log.error("video with the id: {} doesn't exist on the database", videoId);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "video with the id: "+videoId+" doesn't exist on the database"
            );
        }

        Video video= videoOptional.get();

        String thumbnailUrl= fileService.uploadFile(file);
        video.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(video);

        log.info("thumbnail added to the video with the id: {} successfully!", videoId);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "video", videoMapper.mapToVideoResponse(video)
                ),
                "thumbnail added to the video with the id: "+videoId+" successfully!"
        );
    }

    @Override
    public VideoResponse getDetailsVideo(String videoId) {
        Video video= getVideo(videoId);

        increaseVideoCount(video);
        userService.addVideoToHistory(videoId);

        return videoMapper.mapToVideoResponse(video);
    }

    @Override
    public VideoResponse likeVideo(String videoId) {
        Video video= getVideo(videoId);

        if (userService.isLikedVideo(videoId)) {
            video.decrementLikes();
            userService.removeFromLikedVideos(videoId);
        } else if (userService.isDislikedVideo(videoId)) {
            video.decrementDislikes();
            userService.removeFromDislikedVideos(videoId);
            video.incrementLikes();
            userService.addToLikedVideos(videoId);
        } else {
            video.incrementLikes();
            userService.addToLikedVideos(videoId);
        }
        video.setLikeCount(BigDecimal.valueOf(video.getLikes().get()));

        log.info("video: {} liked successfully!", videoId);
        videoRepository.save(video);

        return videoMapper.mapToVideoResponse(video);
    }

    @Override
    public VideoResponse dislikeVideo(String videoId) {
        Video video= getVideo(videoId);

        if (userService.isDislikedVideo(videoId)) {
            video.decrementDislikes();
            userService.removeFromDislikedVideos(videoId);
        } else if (userService.isLikedVideo(videoId)) {
            video.decrementLikes();
            userService.removeFromLikedVideos(videoId);
            video.incrementDislikes();
            userService.addToDislikedVideos(videoId);
        } else {
            video.incrementDislikes();
            userService.addToDislikedVideos(videoId);
        }
        video.setDislikeCount(BigDecimal.valueOf(video.getDislikes().get()));

        log.info("video: {} disliked successfully!", videoId);
        videoRepository.save(video);

        return videoMapper.mapToVideoResponse(video);
    }
 
    @Override
    public void increaseVideoCount(Video video) {
        video.incrementViewCount();
        videoRepository.save(video);
    }

    @Override
    public CommentResponse addComment(String videoId, CommentRequest request) {
        Video video = getVideo(videoId);

        Comment comment = commentMapper.mapToComment(request);
        comment.setVideoId(videoId);
        commentRepository.save(comment);
        log.info("new comment added successfully to video: {}", videoId);

        video.addComment(comment);
        videoRepository.save(video);
        log.info("comment added to video: {}", videoId);

        return commentMapper.mapToCommentResponse(comment);
    }

    @Override
    public Set<CommentResponse> allComments(String videoId) {
        Video video= getVideo(videoId);

        return video.getComments().stream()
                .map(commentMapper::mapToCommentResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<VideoResponse> allVideos(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return videoRepository.findAll(pageable).stream()
                .map(videoMapper::mapToVideoResponse)
                .collect(Collectors.toSet());
    }

    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }
    private Video getVideo(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find video with the id: "+videoId));
    }

    public User getCurrentUser() {
        //final String sub = ((Jwt) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");

        final Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String sub = principal.getClaim("sub");

        return userRepository.findBySub(sub)
                .orElseThrow(()-> new IllegalArgumentException("Can't find user with the sub: "+sub+" into the database!"));
    }
}
