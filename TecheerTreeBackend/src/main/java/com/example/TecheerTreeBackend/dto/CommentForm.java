package com.example.TecheerTreeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentForm {
    private String content;
    private Date created_at;
}
