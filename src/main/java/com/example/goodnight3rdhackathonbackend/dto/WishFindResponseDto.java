package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

@Data
public class WishFindResponseDto {
    private String title;
    private String content;
    private String category;

    public static WishFindResponseDto ofCreate(String title, String content, String category) {
        WishFindResponseDto wishDto = new WishFindResponseDto();
        wishDto.setTitle(title);
        wishDto.setContent(content);
        wishDto.setCategory(category);
        return wishDto;
    }
}
