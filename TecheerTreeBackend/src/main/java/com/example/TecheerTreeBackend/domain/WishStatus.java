package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishConfirmRequest;
import lombok.Getter;

@Getter
public enum WishStatus {
    APPROVED("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");

    private final String description;

    WishStatus(String description){
        this.description = description;
    }


}
