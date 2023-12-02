package com.freetube.videoservice.dto;

import com.freetube.videoservice.entities.Comment;

public interface CommentMapper {
    Comment mapToComment(CommentRequest request);
    CommentResponse mapToCommentResponse(Comment comment);
}
