package com.example.TecheerTreeBackend.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class WishRequest {
    private String title;
    private String content;
    private String category;
    private String is_confirm;
    private Date create_at;
}
