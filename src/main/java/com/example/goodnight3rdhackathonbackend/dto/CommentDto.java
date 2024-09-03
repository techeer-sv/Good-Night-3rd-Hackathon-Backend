package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    private Long wishId;
    private String content;
    private LocalDate createdAt;
}
