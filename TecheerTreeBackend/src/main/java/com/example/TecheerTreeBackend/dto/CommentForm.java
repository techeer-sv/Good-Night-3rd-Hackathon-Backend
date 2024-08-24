package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentForm {
    private String content;
    private Date created_at;

    public static CommentForm createComment(Comment comment) {
        return new CommentForm(
                comment.getContent(),
                comment.getCreated_at()
        );

    }
}
