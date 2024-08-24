package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishConfirmForm;

public enum WishStatus {
    APPROVED("승인됨"),
    PENDING("보류됨"),
    REJECTED("거절됨");

    private final String description;

    WishStatus(String description){
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    // "승인" 또는 "미승인" 문자열을 WishStatus Enum으로 변환하는 메소드
    public static WishStatus fromClientString(String status) {
        if ("승인".equals(status)) {
            return APPROVED;
        } else {
            return PENDING; // "미승인"은 보류됨(PENDING) 또는 거절됨(REJECTED)으로 간주
        }
    }

    public static WishStatus stringToEnum(WishConfirmForm wishConfirmForm) {
        if ("승인".equals(wishConfirmForm.getConfirm())) {
            return WishStatus.APPROVED;
        } else if ("거절".equals(wishConfirmForm.getConfirm())) {
            return WishStatus.REJECTED;
        }
        return null;
    }

}
