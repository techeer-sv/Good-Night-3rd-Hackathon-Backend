package com.example.techeertree.dto.comment;

import com.example.techeertree.domain.Wish;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentResponseDto {

    @Getter
    @Builder
    public static class CommentInfoResponseDto {
        private Long id;
        private String comment;
        private Long wishId;
        private LocalDateTime createdAt;
    }


}
