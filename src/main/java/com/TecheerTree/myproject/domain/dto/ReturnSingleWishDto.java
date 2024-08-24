package com.TecheerTree.myproject.domain.dto;

import com.TecheerTree.myproject.domain.entitiy.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnSingleWishDto {

    private String title;
    private String content;
    private String category;

    public ReturnSingleWishDto() {
    }

    public ReturnSingleWishDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
