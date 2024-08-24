package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentListResponse {
    private Long id;
    private String content;
    private Date created_at;

    public static CommentListResponse createComment(Comments comments) {
        return new CommentListResponse(
                comments.getId(),
                comments.getContent(),
                comments.getCreated_at()
        );

    }
}
