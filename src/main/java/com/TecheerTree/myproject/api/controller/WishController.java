package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.WishService;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;

    // 소원 등록
    @PostMapping
    public ResponseEntity<Wishes> createWish(@Valid @RequestBody WishCreateDto wishCreateDto){

        Wishes newWish = wishService.saveWish(wishCreateDto);

        return new ResponseEntity<>(newWish, HttpStatus.CREATED);
    }

    // 소원 목록 조회
    // 소원 단일 조회
    // 소원 승인
    // 소원 거절
    // 소원 삭제


}
