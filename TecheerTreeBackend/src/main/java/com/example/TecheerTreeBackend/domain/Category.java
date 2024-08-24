package com.example.TecheerTreeBackend.domain;

public enum Category {
    CAREER("진로"),
    HEALTH("건강"),
    RELATIONSHIP("인간 관계"),
    MONEY("돈"),
    GOAL("목표"),
    ACADEMICS("학업/성적"),
    OTHERS("기타");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // 한글 이름을 기반으로 Enum 변환
    public static Category fromKoreanName(String description) {
        for (Category category : Category.values()) {
            if (category.getDescription().equals(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + description);
    }
}
