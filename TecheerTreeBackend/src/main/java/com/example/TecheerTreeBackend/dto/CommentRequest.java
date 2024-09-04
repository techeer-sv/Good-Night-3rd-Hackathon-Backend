package com.example.TecheerTreeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentRequest {
    private String content;
    private Date createdAt;
}
