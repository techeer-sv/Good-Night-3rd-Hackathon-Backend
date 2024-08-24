package com.example.TecheerTreeBackend.controller;

import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.dto.WishListResponse;
import com.example.TecheerTreeBackend.dto.WishResponse;
import com.example.TecheerTreeBackend.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishController {
    @Autowired
    private WishService wishService;

    // 소원 등록
    @PostMapping("/wish")
    public String createWish(@RequestBody WishForm wishForm){
        // 서비스 위임
        return wishService.createWish(wishForm);
    }

    // 소원 삭제(soft delete)
    @PatchMapping("/wish/delete/{wishId}")
    public String deleteWish(@PathVariable Long wishId){
        // 서비스 위임
        return wishService.deleteWish(wishId);
    }

    // 소원 승인 or 거절 처리
    @PatchMapping("/wish/confirm/{wishId}")
    public String confirmWish(@PathVariable Long wishId, @RequestBody WishConfirmForm wishConfirmForm){
        // 서비스 위임
        return wishService.confirmWish(wishId, wishConfirmForm);
    }

    // 소원 단일 조회
    @GetMapping("/wish/{wishId}")
    public ResponseEntity<WishResponse> viewWish(@PathVariable Long wishId){
        // 서비스 위임
        WishResponse viewWish = wishService.viewService(wishId);

        // 만약 viewWish가 null이라면 BAD_REQUEST 반환
        if (viewWish == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(viewWish);
    }

    // 소원 목록 조회
    @GetMapping("/wish")
    public ResponseEntity<List<WishListResponse>> viewWishList(@RequestParam Boolean isConfirm){
        // 서비스 위임
        List<WishListResponse> viewWishList = wishService.viewWishList(isConfirm);

        return ResponseEntity.status(HttpStatus.OK).body(viewWishList);
    }

}
