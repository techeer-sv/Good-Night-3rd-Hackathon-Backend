package com.TecheerTree.myproject.domain.entitiy;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    APPROVED("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");

    private final String description;

    private static final Map<String, Status> STR_TO_ENUM = new HashMap<>();

    static {
        for (Status status : values()) {
            STR_TO_ENUM.put(status.description, status);
        }
    }

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Status fromDescription(String description){
        return STR_TO_ENUM.get(description);
    }
}

