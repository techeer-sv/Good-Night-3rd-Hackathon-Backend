package com.TecheerTree.myproject.domain.dto.response;

import com.TecheerTree.myproject.domain.entitiy.Wish;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishDetailResponse {

    private String title;
    private String content;
    private String category;

    public WishDetailResponse() {
    }

    public WishDetailResponse(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public static WishDetailResponse ofCreate(Wish findWish){
        return new WishDetailResponse(
                findWish.getTitle(),
                findWish.getContent(),
                findWish.getCategory().getKoreanName()
        );
    }
}
