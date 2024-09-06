package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Category;
import com.example.TecheerTreeBackend.domain.Wishes;
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

    public static WishListResponse createWishListDto(Wishes wishes) {
        return new WishListResponse(wishes.getId(), wishes.getTitle(), wishes.getCategory(), wishes.getCreatedAt());
    }
}
