package com.example.Good_Night_3rd_Hackathon_Backend.controller;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.service.WishesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishesController {
    private final WishesService wishesService;

    @Autowired
    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService;
    }

    @PostMapping("/wishes")
    public ResponseEntity<String> createWishes(@Valid @RequestBody Wishes wish) {
        try {
            wishesService.createWish(wish);
            return ResponseEntity.status(HttpStatus.CREATED).body("Wishes successfully created.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create wishes.");
        }
    }

}
