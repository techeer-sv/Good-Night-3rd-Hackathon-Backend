package com.example.techeertree.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 403 FORBIDDEN
    NOT_CONFIRMED (FORBIDDEN, "해당 소원은 승인이 거부된 소원입니다."),

    // 404 NOT_FOUND ERROR
    NOT_EXIST_WISH(NOT_FOUND, "해당 소원이 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;
}
