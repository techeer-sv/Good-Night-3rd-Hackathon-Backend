package com.TecheerTree.myproject.domain.dto.response;

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


}
