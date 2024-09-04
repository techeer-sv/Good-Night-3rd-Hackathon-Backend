package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.Category;
import com.example.TecheerTreeBackend.domain.WishStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class WishRequest {
    private String title;
    private String content;
    private Category category;
    private WishStatus wishStatus;
    private Date createAt;
}
