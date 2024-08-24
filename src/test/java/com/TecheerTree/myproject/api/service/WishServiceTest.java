package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.controller.WishController;
import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import com.TecheerTree.myproject.exception.InvalidCategoryWishException;
import com.TecheerTree.myproject.exception.InvalidStatusWishException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class WishServiceTest {

    @Autowired
    private WishService wishService;

    @Autowired
    private WishController wishController;

    @Autowired
    private WishRepository wishRepository;

    private WishCreateDto wishCreateDto;


    @BeforeEach
    void setUp(){
        wishCreateDto = new WishCreateDto();
        wishCreateDto.setTitle("테스트");
        wishCreateDto.setCategoryName("진로");
        wishCreateDto.setContent("테스트 데이터입니다.");
    }

    // 저장 테스트
    @Test
    public void saveWish() {
        Wishes newWish = wishService.saveWish(wishCreateDto);
        assertNotNull(newWish);
    }

    // 카테고리 예외 테스트
    @Test
    public void saveInvalidCategoryWish(){
        wishCreateDto.setCategoryName("모르겠는데요");
        assertThrows(InvalidCategoryWishException.class, () -> {
            wishService.saveWish(wishCreateDto);
        });
    }

    // soft delete 테스트
    @Test
    public void deleteWish(){
        Long wishId = 3L; // 존재하는 wishId로 변경
        wishService.wishDelete(wishId);

        Wishes wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));

        assertTrue(wish.isDeleted_at()); // true로 저장되었는지 확인

    }

    // status update 테스트
    @Test
    public void updateStatus(){
        Long wishId = 4L; // 존재하는 wishId로 변경
        wishService.updateWishStatus(wishId, "거절됨");

        Wishes wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));

        assertEquals(Status.REJECTED, wish.getIs_confirm()); // REJECTED로 변경되었는지 확인
    }

    // status update 실패 테스트
    @Test
    public void updateInvalidStatus(){
        Long wishId = 4L; // 존재하는 wishId로 변경
        assertThrows(InvalidStatusWishException.class, () -> {
            wishService.updateWishStatus(wishId, "모르겠음");
        });
    }
}