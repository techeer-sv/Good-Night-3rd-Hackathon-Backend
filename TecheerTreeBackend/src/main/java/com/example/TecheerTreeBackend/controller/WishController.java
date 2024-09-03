package com.example.TecheerTreeBackend.controller;

import com.example.TecheerTreeBackend.domain.WishStatus;
import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.dto.WishListResponse;
import com.example.TecheerTreeBackend.dto.WishResponse;
import com.example.TecheerTreeBackend.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    // 소원 등록
    @PostMapping("")
    public String createWish(@RequestBody WishForm wishForm){
        return wishService.createWish(wishForm);
    }

    // 소원 삭제(soft delete)
    @PatchMapping("/delete/{wishId}")
    public String deleteWish(@PathVariable Long wishId){
        return wishService.deleteWish(wishId);
    }

    // 소원 승인 or 거절 처리
    @PatchMapping("/confirm/{wishId}")
    public String confirmWish(@PathVariable Long wishId, @RequestBody WishConfirmForm wishConfirmForm){
        // "승인" 또는 "거절" 문자열을 WishStatus Enum으로 변환
        WishStatus wishStatus = WishStatus.stringToEnum(wishConfirmForm);

        return wishService.confirmWish(wishId, wishStatus);
    }

    // 소원 단일 조회
    @GetMapping("/{wishId}")
    public ResponseEntity<?> viewWish(@PathVariable Long wishId){
        WishResponse viewWish = wishService.viewService(wishId);

        // 만약 viewWish가 null이라면 BAD_REQUEST 반환
        if (viewWish == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("승인 된 소원이 아니거나 삭제 된 소원입니다..");
        }

        return ResponseEntity.status(HttpStatus.OK).body(viewWish);
    }

    // 소원 목록 조회
    @GetMapping("")
    public ResponseEntity<List<WishListResponse>> viewWishList(@RequestParam String status){
        // "승인" 또는 "미승인" 문자열을 WishStatus Enum으로 변환
        WishStatus wishStatus = WishStatus.fromClientString(status);

        List<WishListResponse> viewWishList = wishService.viewWishList(wishStatus);

        return ResponseEntity.status(HttpStatus.OK).body(viewWishList);
    }

}
