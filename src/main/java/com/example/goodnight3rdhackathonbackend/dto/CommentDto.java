package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    private String content;
    private LocalDate created_at;
}
