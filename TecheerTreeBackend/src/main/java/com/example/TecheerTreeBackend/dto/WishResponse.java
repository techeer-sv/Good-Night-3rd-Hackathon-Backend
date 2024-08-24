package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Category;
import com.example.TecheerTreeBackend.domain.Wishes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishResponse {
    private String title;
    private String content;
    private Category category;

    public static WishResponse createWishDto(Wishes wishes) {
            return new WishResponse(wishes.getTitle(), wishes.getContent(), wishes.getCategory());
    }
}
