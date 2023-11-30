package com.freetube.videoservice.service;

import com.freetube.videoservice.Utils.Response;
import com.freetube.videoservice.dto.UploadVideoResponse;
import com.freetube.videoservice.dto.VideoMapper;
import com.freetube.videoservice.dto.VideoRequest;
import com.freetube.videoservice.dto.VideoResponse;
import com.freetube.videoservice.entities.Video;
import com.freetube.videoservice.enumeration.VideoStatus;
import com.freetube.videoservice.repository.UserRepository;
import com.freetube.videoservice.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VideoServiceImpl implements VideoService{
    private final FileService fileService;
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserService userService;


    @Override
    public UploadVideoResponse uploadVideo(MultipartFile file) {
        String videoUrl= fileService.uploadFile(file);

        var video= new Video();
        video.setVideoUrl(videoUrl);

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
        Optional<Video> videoOptional= videoRepository.findById(videoId);
        if (videoOptional.isEmpty()) {
            log.error("video with the id: {} doesn't exist on the database", videoId);
            return null;
        }

        Video video= videoOptional.get();

        return videoMapper.mapToVideoResponse(video);
    }

    @Override
    public Response likeVideo(String videoId) {
        Video video= videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find video with the id: "+videoId+" into the database!"));

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

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "video", videoMapper.mapToVideoResponse(video)
                ),
                "video: "+videoId+" liked successfully!"
        );
    }

    @Override
    public Response dislikeVideo(String videoId) {
        Video video= videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find video with the id: "+videoId));

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

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "video", videoMapper.mapToVideoResponse(video)
                ),
                "video: "+videoId+" disliked successfully!"
        );
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
}
