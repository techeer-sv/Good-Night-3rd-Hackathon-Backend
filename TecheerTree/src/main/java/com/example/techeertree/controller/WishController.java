package com.example.techeertree.controller;

import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.wish.WishRequestDto;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("api/wish")
@RestController
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @Operation(summary = "소원 등록")
    @PostMapping
    public ResponseEntity<WishInfoResponseDto> createWish(@Valid @RequestBody WishRequestDto requestDto){
        WishInfoResponseDto responseDto = wishService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "소원 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id){
        wishService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

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
                    .isConfirm(wish.getIsConfirm())
                    .createdAt(wish.getCreatedAt())
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

}
