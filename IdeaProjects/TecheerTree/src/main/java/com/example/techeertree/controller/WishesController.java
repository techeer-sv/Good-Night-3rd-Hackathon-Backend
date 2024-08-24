package com.example.techeertree.controller;

import com.example.techeertree.service.WishService;
import com.example.techeertree.entity.Wish;
import com.example.techeertree.entity.Wish.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/wishes")
public class WishesController {

    private final WishService wishService;

    @Autowired
    public WishesController(WishService wishService) {
        this.wishService = wishService;
    }

    // JSON 데이터로 소원을 생성하는 메서드
    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody Wish wishRequest) {
        Wish wish = wishService.createWish(wishRequest.getTitle(), wishRequest.getContent(), wishRequest.getCategory());
        return new ResponseEntity<>(wish, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveWish(@PathVariable Long id) {
        wishService.approveWish(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectWish(@PathVariable Long id) {
        wishService.rejectWish(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wish> getWish(@PathVariable Long id) {
        Wish wish = wishService.getWish(id);
        return ResponseEntity.ok(wish);
    }

    @GetMapping
    public ResponseEntity<Page<Wish>> getWishList(@RequestParam(required = false) Wish.ApprovalStatus status,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Wish> wishes = wishService.getWishList(status, pageable);
        return ResponseEntity.ok(wishes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Wish>> searchWishes(@RequestParam String keyword,
                                                   @RequestParam(required = false) Category category,
                                                   @RequestParam int page,
                                                   @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        List<Wish> wishes = wishService.searchWishes(keyword, category, pageable);
        return ResponseEntity.ok(wishes);
    }
}