package com.example.techeertree.controller;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.wish.WishRequestDto.*;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("api/wishes")
@RestController
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @Operation(summary = "소원 등록")
    @PostMapping
    public ResponseEntity<WishInfoResponseDto> createWish(@Valid @RequestBody WishCreateRequestDto requestDto){
        WishInfoResponseDto responseDto = wishService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "소원 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id){
        wishService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "'보류됨'상태의 소원 조회")
    @GetMapping("/pending")
    public ResponseEntity<List<WishInfoResponseDto>> getPendingWishes() {
        List<Wish> pendingWishes = wishService.getPendingWishes();
        List<WishInfoResponseDto> responseDtos= new ArrayList<>();
        for(Wish wish : pendingWishes){
            WishInfoResponseDto responseDto = WishInfoResponseDto.builder()
                    .id(wish.getId())
                    .title(wish.getTitle())
                    .content(wish.getContent())
                    .category(wish.getCategory())
                    .build();
            responseDtos.add(responseDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @Operation(summary = "승인/거절 처리")
    @PatchMapping("/{id}")
    public ResponseEntity<WishUpdateResponseDto> updateWish(@PathVariable Long id,
                                                                        @RequestParam("status")Confirm status){
        WishUpdateResponseDto responseDto = wishService.update(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "소원 단일 조회")
    @GetMapping("/{id}")
    public ResponseEntity<WishInfoResponseDto> findOne(@PathVariable Long id){
        WishInfoResponseDto responseDto = wishService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "소원 목록 조회")
    @GetMapping
    public ResponseEntity<Page<WishListResponseDto>> findAllWishes(@RequestParam Confirm confirm,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "5") int size ){

        Page<WishListResponseDto> wishes = wishService.findAllWishes(confirm, page, size);

        return ResponseEntity.status(HttpStatus.OK).body(wishes);

    }

    @Operation(summary = "키워드 기반 소원 검색")
    @GetMapping("/search")
    public ResponseEntity<List<WishInfoResponseDto>> searchWishes(@RequestParam String keyword,
                                                                  @RequestParam(required = false) Category category){
        List<WishInfoResponseDto> wishes = wishService.searchWishes(keyword, category);

        if (wishes.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(wishes);
    }


}
