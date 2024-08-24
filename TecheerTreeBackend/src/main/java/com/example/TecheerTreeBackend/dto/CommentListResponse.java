package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentListResponse {
    private Long id;
    private String content;
    private Date created_at;

    public static CommentListResponse createComment(Comment comment) {
        return new CommentListResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreated_at()
        );

    }
}
