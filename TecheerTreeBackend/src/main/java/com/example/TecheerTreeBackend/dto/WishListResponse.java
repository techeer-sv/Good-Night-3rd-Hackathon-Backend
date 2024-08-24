package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WishListResponse {
    private String title;
    private String category;
    private Date createAt;

    public static WishListResponse createWishListDto(Wish wish) {
        return new WishListResponse(wish.getTitle(), wish.getCategory(), wish.getCreated_at());
    }
}
