package com.TecheerTree.myproject.domain.dto;

import com.TecheerTree.myproject.domain.entitiy.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishCreateDto {

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    private String categoryName;

    // String으로 받은 category를 ENUM에 맞게 변환
    public Category getCategory(){
        return Category.fromKoreanName(categoryName);
    }

    public WishCreateDto() {
    }

    public WishCreateDto(String title, String content, String categoryName) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
    }


}

