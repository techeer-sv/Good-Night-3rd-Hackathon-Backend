package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class WishServiceTest {

    @Autowired
    private WishService wishService;
    private WishCreateDto wishCreateDto;


    @BeforeEach
    void setUp(){
        wishCreateDto = new WishCreateDto();
        wishCreateDto.setTitle("테스트");
        wishCreateDto.setCategory(Category.STUDY);
        wishCreateDto.setContent("테스트 데이터입니다.");
    }

    @Test
    public void saveWish() {
        Wishes newWish = wishService.saveWish(wishCreateDto);
        assertNotNull(newWish);
    }
}