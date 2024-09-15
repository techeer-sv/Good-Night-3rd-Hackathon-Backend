package com.example.techeertree.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentRequestDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentCreateRequestDto {

        @NotBlank(message = "댓글은 필수 입력 항목입니다.")
        private String comment;

        private LocalDateTime createdAt;
    }
}
