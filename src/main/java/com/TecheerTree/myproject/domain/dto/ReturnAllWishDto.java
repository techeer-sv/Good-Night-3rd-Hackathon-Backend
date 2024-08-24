package com.TecheerTree.myproject.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ReturnAllWishDto {

    private String title;
    private String category;
    private LocalDate createdDate;

    public ReturnAllWishDto() {
    }

    public ReturnAllWishDto(String title, String category, LocalDate createdDate) {
        this.title = title;
        this.category = category;
        this.createdDate = createdDate;
    }
}
