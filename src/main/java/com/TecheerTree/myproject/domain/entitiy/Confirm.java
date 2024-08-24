package com.TecheerTree.myproject.domain.entitiy;

public enum Confirm {
    APPROVED("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");

    private final String description;

    Confirm(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

