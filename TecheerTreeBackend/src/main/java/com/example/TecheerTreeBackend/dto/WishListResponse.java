package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Category;
import com.example.TecheerTreeBackend.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WishListResponse {
    private Long id;
    private String title;
    private Category category;
    private Date createAt;

    public static WishListResponse createWishListDto(Wish wish) {
        return new WishListResponse(wish.getId(), wish.getTitle(), wish.getCategory(), wish.getCreated_at());
    }
}
