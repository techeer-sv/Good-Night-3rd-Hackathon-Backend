package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.WishService;
import com.TecheerTree.myproject.domain.dto.request.WishSearchRequest;
import com.TecheerTree.myproject.domain.dto.response.WishResponse;
import com.TecheerTree.myproject.domain.dto.response.WishDetailResponse;
import com.TecheerTree.myproject.domain.dto.request.WishSaveRequest;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;

    // 소원 등록
    @PostMapping
    public ResponseEntity<Wishes> createWish(@Valid @RequestBody WishSaveRequest wishSaveRequest){
        Wishes newWish = wishService.saveWish(wishSaveRequest);
        return new ResponseEntity<>(newWish, HttpStatus.CREATED);
    }

    // 소원 목록 조회
    @GetMapping
    public ResponseEntity<Page<WishResponse>> getWishes(
            @RequestParam(value = "status",required = false) Status status,
            @PageableDefault(sort = "createdDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable ){
        // 상태가 제공된 경우 해당 상태로 필터링, 상태가 제공되지 않은 경우 전체 조회
        Page<WishResponse> response = wishService.getWishesAsResponse(status, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 소원 단일 조회
    @GetMapping("/{wishId}")
    public ResponseEntity<WishDetailResponse> getWish(@PathVariable("wishId") Long wishId){
        WishDetailResponse findWish = wishService.getWish(wishId);
        return new ResponseEntity<>(findWish, HttpStatus.OK);
    }


    // 소원 승인/거절
    @PatchMapping("/{wishId}/status")
    public ResponseEntity<Wishes> updateWishStatus(@PathVariable("wishId") Long wishId,
                                                   @RequestParam("description") String description){
        Wishes updatedWish = wishService.updateWishStatus(wishId, description);
        return new ResponseEntity<>(updatedWish,HttpStatus.OK);
    }

    // 소원 삭제
    @DeleteMapping("/{wishId}")
    public ResponseEntity<String> deleteWish(@PathVariable("wishId") Long wishId) {
       wishService.wishDelete(wishId);
       return ResponseEntity.ok("삭제에 성공하였습니다.");
    }

    // 소원 검색
    @GetMapping("/search")
    public ResponseEntity<List<Wishes>> searchWish(@RequestBody WishSearchRequest wishSearchRequest){
        List<Wishes> searchWish = wishService.searchWishes(wishSearchRequest);
        return new ResponseEntity<>(searchWish,HttpStatus.OK);
    }
}
