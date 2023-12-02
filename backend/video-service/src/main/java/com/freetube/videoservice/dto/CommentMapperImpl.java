package com.freetube.videoservice.dto;

import com.freetube.videoservice.entities.Comment;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentMapperImpl implements CommentMapper{
    @Override
    public Comment mapToComment(CommentRequest request) {
        return Comment.builder()
                .authorId(request.getAuthorId())
                .text(request.getText())
                .build();
    }

    @Override
    public CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .authorId(comment.getAuthorId())
                .text(comment.getText())
                .videoId(comment.getVideoId())
                .likeCount(comment.getLikeCount())
                .dislikeCount(comment.getDislikeCount())
                .build();
    }
}
