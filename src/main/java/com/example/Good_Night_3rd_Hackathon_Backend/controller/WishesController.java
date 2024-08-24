package com.example.Good_Night_3rd_Hackathon_Backend.controller;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.dto.WishesListDto;
import com.example.Good_Night_3rd_Hackathon_Backend.service.WishesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            Long id = wishesService.createWish(wish);
            return ResponseEntity.status(HttpStatus.CREATED).body("Wishes successfully created. id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create wishes.");
        }
    }

    @DeleteMapping("/wishes/{id}")
    public ResponseEntity<String> deleteWishes(@PathVariable Long id) {
        try {
            wishesService.deleteWish(id);
            return ResponseEntity.status(HttpStatus.OK).body("Wishes successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete wishes.");
        }
    }

    @PatchMapping("/wishes/{id}")
    public ResponseEntity<String> confirmWishes(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        try {
            String confirmStatus = updates.get("confirmStatus");
            wishesService.confirmWish(id, ConfirmStatus.valueOf(confirmStatus));
            return ResponseEntity.status(HttpStatus.OK).body("Wishes successfully confirmed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to confirm wishes.");
        }
    }

    @GetMapping("/wishes/{id}")
    public ResponseEntity<Wishes> getWishes(@PathVariable Long id) {
        try {
            Optional<Wishes> wish = wishesService.getWish(id);
            if (wish.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(wish.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/wishes")
    public ResponseEntity<List<WishesListDto>> getWishes(@RequestParam ConfirmStatus status, @RequestParam int page, @RequestParam int size) {
        try {
            Page<WishesListDto> wishesPage = wishesService.getWishesByStatus(status, page, size);
            System.out.println(wishesPage.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(wishesPage.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
