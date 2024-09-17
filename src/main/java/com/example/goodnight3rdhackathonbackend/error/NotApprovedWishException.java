package com.example.goodnight3rdhackathonbackend.error;

public class NotApprovedWishException extends RuntimeException {
    public NotApprovedWishException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
