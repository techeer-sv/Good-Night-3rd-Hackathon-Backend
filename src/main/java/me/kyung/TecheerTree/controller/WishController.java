package me.kyung.TecheerTree.controller;

import lombok.Getter;
import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.dto.response.WishDetailResponse;
import me.kyung.TecheerTree.dto.response.WishListResponse;
import me.kyung.TecheerTree.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    @Autowired
    private WishService wishService;

    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> findWish(@PathVariable("id") Long id){
        return ResponseEntity.ok(wishService.findWish(id));
    }

    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody Wish wish) {
        return ResponseEntity.ok(wishService.createWish(wish));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable("id") Long id) {
        wishService.deleteWish(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve")
    public  ResponseEntity<Wish> approveWish(@PathVariable("id")Long id){
        wishService.updateWish(id, Wish.Status.APPROVED);
        return ResponseEntity.ok(wishService.updateWish(id, Wish.Status.APPROVED));
    }

    @PatchMapping("/{id}/reject")
    public  ResponseEntity<Wish> rejectWish(@PathVariable("id")Long id){
        wishService.updateWish(id, Wish.Status.REJECTED);
        return ResponseEntity.ok(wishService.updateWish(id, Wish.Status.REJECTED));
    }





}