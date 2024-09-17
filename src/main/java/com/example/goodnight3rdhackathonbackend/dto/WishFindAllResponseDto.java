package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WishFindAllResponseDto {
    private String title;
    private String category;
    private LocalDate createdAt;

    public static WishFindAllResponseDto ofCreate(String title, String category, LocalDate createdAt) {
        WishFindAllResponseDto wishDto = new WishFindAllResponseDto();
        wishDto.setTitle(title);
        wishDto.setCategory(category);
        wishDto.setCreatedAt(createdAt);
        return wishDto;

    }
}
