package com.example.goodnight3rdhackathonbackend.error;

public class NotFoundWishException extends RuntimeException {
    public NotFoundWishException(ErrorCode errorCode){
        super(errorCode.getMessage());
    }
}
