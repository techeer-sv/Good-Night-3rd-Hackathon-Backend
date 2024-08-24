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
}
