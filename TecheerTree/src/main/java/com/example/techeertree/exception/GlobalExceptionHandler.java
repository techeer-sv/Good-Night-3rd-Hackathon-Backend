package com.example.techeertree.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorResponseDto> errorResponseDtos = new ArrayList<>();

        for(FieldError fieldError: fieldErrors){
            ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                    .code(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            errorResponseDtos.add(errorResponseDto);
        }

        return new ResponseEntity<>(errorResponseDtos, HttpStatus.BAD_REQUEST);
    }
}
