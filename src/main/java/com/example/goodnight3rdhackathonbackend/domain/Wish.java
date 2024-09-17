package com.example.goodnight3rdhackathonbackend.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Wish {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDate createdAt;
    private String isConfirm = WishConfirmState.PENDING.getKorean();
    private boolean isDeleted = false;

    public static Wish ofCreate(String title, String content, String category, LocalDate createdAt) {
        Wish wish = new Wish();
        wish.setTitle(title);
        wish.setContent(content);
        wish.setCategory(category);
        wish.setCreatedAt(createdAt);
        return wish;
    }
}
