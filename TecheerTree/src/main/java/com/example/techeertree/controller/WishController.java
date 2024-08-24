package com.example.techeertree.controller;

import com.example.techeertree.dto.wish.WishRequestDto;
import com.example.techeertree.dto.wish.WishResponseDto;
import com.example.techeertree.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/wish")
@RestController
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @Operation(summary = "소원 등록")
    @PostMapping
    public ResponseEntity<WishResponseDto> createWish(@Valid @RequestBody WishRequestDto requestDto){
        WishResponseDto responseDto = wishService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
