package com.example.techeertree.dto.wish;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class WishRequestDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class WishCreateRequestDto {

    @NotBlank(message="제목은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message="내용은 필수 입력 항목입니다.")
    private String content;

    @NotNull(message="카테고리는 필수 선택 항목입니다.")
    private Category category;

    private LocalDateTime createdAt;

    }



}
