package com.example.techeertree.controller;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import com.example.techeertree.dto.wish.WishMapper.*;
import com.example.techeertree.dto.wish.WishRequestDto.*;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/wishes")
@RestController
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @Operation(summary = "소원 등록")
    @PostMapping
    public ResponseEntity<WishInfoResponseDto> createWish(@Valid @RequestBody WishCreateRequestDto requestDto){
        WishEntity wish = wishService.create(requestDto);
        WishInfoResponseDto responseDto = WishCreateMapper.INSTANCE.toDto(wish);

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
        List<WishEntity> pendingWishes = wishService.getPendingWishes();
        List<WishInfoResponseDto> responseDtos= pendingWishes.
                stream().map(w -> WishInfoResponseDto.ofCreate(
                                                            w.getId(),
                                                            w.getTitle(),
                                                            w.getContent(),
                                                            w.getCategory())).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @Operation(summary = "승인/거절 처리")
    @PatchMapping("/{id}")
    public ResponseEntity<WishUpdateResponseDto> updateWish(@PathVariable Long id,
                                                                        @RequestParam("status")Confirm status){
        WishEntity wish = wishService.update(id, status);
        WishUpdateResponseDto responseDto = WishUpdateMapper.INSTANCE.toDto(wish);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "소원 단일 조회")
    @GetMapping("/{id}")
    public ResponseEntity<WishInfoResponseDto> findOne(@PathVariable Long id){
        WishEntity wish = wishService.findOne(id);
        WishInfoResponseDto responseDto = WishCreateMapper.INSTANCE.toDto(wish);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "소원 목록 조회")
    @GetMapping
    public ResponseEntity<Page<WishListResponseDto>> findAllWishes(@RequestParam Confirm confirm,
                                                                   @PageableDefault(direction = Sort.Direction.DESC,size=10) Pageable pageable ){
        Page<WishEntity> allWishes = wishService.findAllWishes(confirm, pageable);

        Page<WishListResponseDto> wishes = allWishes.map(WishReadMapper.INSTANCE::toDto);

       return ResponseEntity.status(HttpStatus.OK).body(wishes);

    }

    @Operation(summary = "키워드 기반 소원 검색")
    @GetMapping("/search")
    public ResponseEntity<List<WishInfoResponseDto>> searchWishes(@RequestParam String keyword,
                                                                  @RequestParam(required = false) Category category){
        List<WishEntity> wishes = wishService.searchWishes(keyword, category);
        List<WishInfoResponseDto> results = wishes.stream().map(WishCreateMapper.INSTANCE::toDto).toList();

        if (results.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }


}
