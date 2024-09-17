
package com.example.goodnight3rdhackathonbackend.controller;


import com.example.goodnight3rdhackathonbackend.domain.Wish;
import com.example.goodnight3rdhackathonbackend.dto.WishConfirmRequestDto;
import com.example.goodnight3rdhackathonbackend.dto.WishFindAllResponseDto;
import com.example.goodnight3rdhackathonbackend.dto.WishFindResponseDto;
import com.example.goodnight3rdhackathonbackend.dto.WishSaveRequestDto;
import com.example.goodnight3rdhackathonbackend.service.WishService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/wishs")
    public void saveWish(@RequestBody WishSaveRequestDto wishDto) {
        Wish wish = Wish.ofCreate(wishDto.getTitle(), wishDto.getContent(), wishDto.getCategory(), LocalDate.now());
        wishService.saveWish(wish);
    }

    @DeleteMapping("/wishs/{id}")
    public void deleteWishById(@PathVariable Long id) {
        wishService.deleteWishById(id);
    }

    @PutMapping("/wishs/{id}")
    public void confirmWishById(@PathVariable Long id, @RequestBody WishConfirmRequestDto wishDto) {

        wishService.confirmWishById(id, wishDto.getIsConfirm());
    }

    @GetMapping("wishs/{id}")
    public WishFindResponseDto findWishById(@PathVariable Long id) {
        return wishService.findWishById(id);
    }

    @GetMapping("/wishs")
    public List<WishFindAllResponseDto> findAllWish(@RequestParam(value = "state", required = false) String wishState,
                                                    @RequestParam(value = "page", defaultValue = "0") int page) {
        return wishService.findAllWish(wishState, page);
    }

}
