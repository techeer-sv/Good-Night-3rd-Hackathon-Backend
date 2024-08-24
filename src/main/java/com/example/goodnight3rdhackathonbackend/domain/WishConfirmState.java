package com.example.goodnight3rdhackathonbackend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WishConfirmState {

    APPROVED("승인됨", "approved"),
    PENDING("보류됨", "pending"),
    REJECTED("거절됨", "rejected");


    private final String korean;
    private final String english;
}
