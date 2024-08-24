package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.WishService;
import com.TecheerTree.myproject.domain.dto.ReturnAllWishDto;
import com.TecheerTree.myproject.domain.dto.ReturnSingleWishDto;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @GetMapping
    public ResponseEntity<Page<ReturnAllWishDto>> getWishes(
            @RequestParam(value = "status",required = false) Status status,
            @PageableDefault(sort = "createdDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable ){

        // 상태가 제공된 경우 해당 상태로 필터링, 상태가 제공되지 않은 경우 전체 조회
        Page<Wishes> wishes = wishService.getWishes(status, pageable);

        Page<ReturnAllWishDto> response = wishes.map(wish -> new ReturnAllWishDto(
                wish.getTitle(),
                wish.getContent(),
                wish.getCreatedDate()
        ));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 소원 단일 조회
    @GetMapping("/{wishId}")
    public ResponseEntity<ReturnSingleWishDto> getWish(@PathVariable("wishId") Long wishId){
        ReturnSingleWishDto findWish = wishService.getWish(wishId);
        return new ResponseEntity<>(findWish, HttpStatus.OK);
    }


    // 소원 승인/거절
    @PatchMapping("/{wishId}/status")
    public ResponseEntity<Wishes> updateWishStatus(@PathVariable("wishId") Long wishId,
                                                   @RequestParam("description") String description){
        System.out.println("description: "+ description);
        Wishes updatedWish = wishService.updateWishStatus(wishId, description);
        return new ResponseEntity<>(updatedWish,HttpStatus.OK);
    }

    // 소원 삭제
    @DeleteMapping("/{wishId}")
    public ResponseEntity<String> deleteMember(@PathVariable("wishId") Long wishId) {
       wishService.wishDelete(wishId);
       return ResponseEntity.ok("삭제에 성공하였습니다.");
    }
}
