package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WishFindAllResponseDto {
    private String title;
    private String category;
    private LocalDate createdAt;
}
