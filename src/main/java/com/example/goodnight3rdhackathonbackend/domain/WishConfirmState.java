package com.example.goodnight3rdhackathonbackend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WishConfirmState {

    APPROVED("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");


    private final String korean;
}
