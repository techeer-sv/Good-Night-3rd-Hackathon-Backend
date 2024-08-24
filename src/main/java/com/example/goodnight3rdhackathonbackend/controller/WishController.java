
package com.example.goodnight3rdhackathonbackend.controller;


import com.example.goodnight3rdhackathonbackend.dto.WishDto;
import com.example.goodnight3rdhackathonbackend.service.WishService;
import org.springframework.web.bind.annotation.*;


@RestController
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/wishs")
    public void saveWish(@RequestBody WishDto.SaveDto wishDto) {
        wishService.saveWish(wishDto);
    }

    @DeleteMapping("/wishs/{id}")
    public void deleteWishById(@PathVariable Long id) {
        wishService.deleteWishById(id);
    }

    @PutMapping("/wishs/{id}")
    public void confirmWishById(@PathVariable Long id, @RequestBody WishDto.ConfirmDto wishDto) {
        wishService.confirmWishById(id, wishDto);
    }






}
