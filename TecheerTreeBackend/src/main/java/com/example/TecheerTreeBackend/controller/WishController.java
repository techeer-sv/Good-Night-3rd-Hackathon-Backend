package com.example.TecheerTreeBackend.controller;

import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {
    @Autowired
    private WishService wishService;

    @PostMapping("/wish")
    public String createWish(@RequestBody WishForm wishForm){
            // 서비스 위임
            return wishService.createWish(wishForm);
    }

}
