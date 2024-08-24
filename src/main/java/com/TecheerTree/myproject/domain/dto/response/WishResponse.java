package com.TecheerTree.myproject.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class WishResponse {
    private Long wishId;
    private String title;
    private String category;
    private LocalDate createdDate;


    public WishResponse(Long wishId , String title, String category, LocalDate createdDate) {
        this.wishId = wishId;
        this.title = title;
        this.category = category;
        this.createdDate = createdDate;
    }
}
