package com.TecheerTree.myproject.domain.entitiy;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Comments {
    private String content;
    private LocalDate createdDate;
    private Wishes wishes;

    public Comments() {
    }

    public Comments(String content, LocalDate createdDate, Wishes wishes) {
        this.content = content;
        this.createdDate = createdDate;
        this.wishes = wishes;
    }
}
