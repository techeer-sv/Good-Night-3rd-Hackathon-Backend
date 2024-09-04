package com.example.TecheerTreeBackend.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class WishRequest {
    private String title;
    private String content;
    private String category;
    private String wishStatus;
    private Date createAt;
}
