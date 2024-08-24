package com.TecheerTree.myproject.domain.entitiy;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    CAREER("진로"),
    HEALTH("건강"),
    RELATIONSHIP("인간 관계"),
    MONEY("돈"),
    GOALS("목표"),
    STUDY("학업/성적"),
    OTHERS("기타");

    private final String koreanName;

    private static final Map<String, Category> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Category category : values()) {
            NAME_TO_ENUM.put(category.koreanName, category);
        }
    }

    Category(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static Category fromKoreanName(String koreanName) {
        return NAME_TO_ENUM.get(koreanName);
    }

}