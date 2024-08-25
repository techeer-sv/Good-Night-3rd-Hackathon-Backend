package com.TecheerTree.myproject.domain.dto.response;

import com.TecheerTree.myproject.domain.entitiy.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class WishResponse {
    private Long wishId;
    private String title;
    private Category category;
    private LocalDate createdDate;


    public WishResponse(Long wishId , String title, Category category, LocalDate createdDate) {
        this.wishId = wishId;
        this.title = title;
        this.category = category;
        this.createdDate = createdDate;
    }
}
