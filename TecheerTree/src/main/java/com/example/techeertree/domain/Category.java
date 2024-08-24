package com.example.techeertree.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    CAREER("진로"),
    HEALTH("건강"),
    PERSON("인간관계"),
    MONEY("돈"),
    GOAL("목표"),
    STUDY("학업/성적"),
    OTHER("기타");

    private final String name;
}
