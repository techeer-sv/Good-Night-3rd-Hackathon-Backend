package com.example.TecheerTreeBackend.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CommentForm {
    private String content;
    private Date created_at;
}
