package com.example.goodnight3rdhackathonbackend.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_EXIST_WISH("해당 소원글은 존재하지 않습니다."),
    NOT_APPROVED_WISH("해당 소원글은 승인되지 않았습니다.");

    private final String message;
}
