package com.example.techeertree.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Confirm {
    CONFIRM("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");

    private final String status;
}