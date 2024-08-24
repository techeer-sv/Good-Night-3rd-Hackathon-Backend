package me.kyung.TecheerTree.controller;

import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/wishes")
public class WishController {

    @Autowired
    private WishService wishService;

    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody Wish wish) {
        return ResponseEntity.ok(wishService.createWish(wish));
    }


}