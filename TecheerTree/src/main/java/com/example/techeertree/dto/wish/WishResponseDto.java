package com.example.techeertree.dto.wish;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


public class WishResponseDto {

    @Builder
    @Getter
    public static class WishInfoResponseDto {

    private Long id;
    private String title;
    private String content;
    private Category category;
    }

    @Getter
    @Builder
    public static class WishUpdateResponseDto {
        private Long id;
        private Confirm isConfirm;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Builder
    public static class WishListResponseDto {
        private String title;
        private Category category;
        private LocalDateTime createdAt;
    }
}
