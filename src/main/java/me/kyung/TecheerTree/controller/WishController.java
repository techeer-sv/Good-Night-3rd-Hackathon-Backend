package me.kyung.TecheerTree.controller;

import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.dto.response.WishDetailResponse;
import me.kyung.TecheerTree.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}