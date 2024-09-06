package com.example.TecheerTreeBackend.domain;


import lombok.Getter;

@Getter
public enum Category {
    CAREER("진로"),
    HEALTH("건강"),
    RELATIONSHIP("인간 관계"),
    MONEY("돈"),
    GOAL("목표"),
    ACADEMICS("학업/성적"),
    OTHERS("기타");

    // 한글 이름을 반환하는 메소드
    private final String koreanName;

    Category(String koreanName){
        this.koreanName = koreanName;
    }

}
