package com.example.TecheerTreeBackend.controller;

import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
