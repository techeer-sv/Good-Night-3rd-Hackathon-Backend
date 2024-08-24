package com.TecheerTree.myproject.domain.dto;

import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CommentCreateDto {

    private String content;
    private Long wishId;  // 소원의 ID만 저장

    public CommentCreateDto() {
    }

    public CommentCreateDto(String content, Long wishId) {
        this.content = content;
        this.wishId = wishId;
    }

}

