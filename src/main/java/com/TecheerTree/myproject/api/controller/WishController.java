package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.WishService;
import com.TecheerTree.myproject.domain.dto.request.WishSearchRequest;
import com.TecheerTree.myproject.domain.dto.response.WishResponse;
import com.TecheerTree.myproject.domain.dto.response.WishDetailResponse;
import com.TecheerTree.myproject.domain.dto.request.WishSaveRequest;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wish;
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
    public ResponseEntity<Wish> createWish(@Valid @RequestBody WishSaveRequest wishSaveRequest){
        //Wishes newWish = wishService.saveWish(wishSaveRequest);
        // service로 전달할 때는 요청 객체 -> 도메인 객체로 변환하여 전달
        Wish newWish = wishService.saveWish(Wish.fromDTO(wishSaveRequest));

        return new ResponseEntity<>(newWish, HttpStatus.CREATED);
    }

    // 소원 목록 조회
    @GetMapping
    public ResponseEntity<Page<WishResponse>> getWishes(
            @RequestParam(value = "status",required = false) Status status,
            @PageableDefault(sort = "createdDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable ){
        // 상태가 제공된 경우 해당 상태로 필터링, 상태가 제공되지 않은 경우 전체 조회
        // Page<WishResponse> response = wishService.getWishesAsResponse(status, pageable);
        // 요청,응답객체는 컨트롤러 레이어에서만 알고있도로 하자, 서비스 레이어에는 도메인 객체를 넘기도록 하자
        Page<WishResponse> response = wishService.getWishes(status, pageable).map(wish -> new WishResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getCategory(),
                wish.getCreatedDate()
        ));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 소원 단일 조회
    @GetMapping("/{wishId}")
    public ResponseEntity<WishDetailResponse> getWish(@PathVariable("wishId") Long wishId){
        //WishDetailResponse findWish = wishService.getWish(wishId);
        // 요청,응답객체는 컨트롤러 레이어에서만 알고있도로 하자, 서비스 레이어에는 도메인 객체를 넘기도록 하자
        WishDetailResponse response = WishDetailResponse.ofCreate(wishService.getWish(wishId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 보류 상태 소원 전체 조회
    @GetMapping("/pending")
    public ResponseEntity<List<Wish>> getPendingWishes(){
        List<Wish> pendingWishes = wishService.getPendingWishes();
        return new ResponseEntity<>(pendingWishes,HttpStatus.OK);
    }

    // 소원 승인/거절
    @PatchMapping("/{wishId}/status")
    public ResponseEntity<Wish> updateWishStatus(@PathVariable("wishId") Long wishId,
                                                 @RequestParam("description") String description) {
        Wish updatedWish = wishService.updateWishStatus(wishId, description);
        return new ResponseEntity<>(updatedWish, HttpStatus.OK);
    }

    // 소원 삭제
    @DeleteMapping("/{wishId}")
    public ResponseEntity<String> deleteWish(@PathVariable("wishId") Long wishId) {
       wishService.wishDelete(wishId);
       return ResponseEntity.ok("삭제에 성공하였습니다.");
    }

    // 소원 검색
    @GetMapping("/search")
    public ResponseEntity<List<Wish>> searchWish(@RequestBody WishSearchRequest wishSearchRequest){
        List<Wish> searchWish = wishService.searchWishes(wishSearchRequest);
        return new ResponseEntity<>(searchWish,HttpStatus.OK);
    }
}
