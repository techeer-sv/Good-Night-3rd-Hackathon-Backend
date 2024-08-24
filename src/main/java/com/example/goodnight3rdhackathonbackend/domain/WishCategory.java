package com.example.goodnight3rdhackathonbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WishCategory {
    CAREER("진로"),
    HEALTH("건강"),
    RELATIONSHIP("인간 관계"),
    MONEY("돈"),
    GOAL("목표"),
    STUDY("학업/성적"),
    ETC("기타");


    private final String korean;
}
