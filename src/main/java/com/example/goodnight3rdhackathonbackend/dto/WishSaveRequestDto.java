package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WishSaveRequestDto {
    private String title;
    private String content;
    private String category;
    private LocalDate createdAt;
}
