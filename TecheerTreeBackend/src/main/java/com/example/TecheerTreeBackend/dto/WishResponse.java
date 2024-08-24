package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Category;
import com.example.TecheerTreeBackend.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishResponse {
    private String title;
    private String content;
    private Category category;

    public static WishResponse createWishDto(Wish wish) {
            return new WishResponse(wish.getTitle(), wish.getContent(), wish.getCategory());
    }
}
