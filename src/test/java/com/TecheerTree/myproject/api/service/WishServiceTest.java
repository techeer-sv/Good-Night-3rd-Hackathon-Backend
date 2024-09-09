package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.controller.WishController;
import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.response.WishDetailResponse;
import com.TecheerTree.myproject.domain.dto.request.WishSaveRequest;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import com.TecheerTree.myproject.global.exception.InvalidCategoryWishException;
import com.TecheerTree.myproject.global.exception.InvalidStatusWishException;
import com.TecheerTree.myproject.global.exception.UnApprovedStatusException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishServiceTest {

    @Autowired
    private WishService wishService;

    @Autowired
    private WishRepository wishRepository;

    private WishSaveRequest wishSaveRequest;


    @BeforeEach
    void setUp(){
        wishSaveRequest = new WishSaveRequest();
        wishSaveRequest.setTitle("테스트234");
        wishSaveRequest.setCategoryName("학업/성적");
        wishSaveRequest.setContent("테스트234 데이터입니다.");
    }

    // 저장 테스트
    @Test
    public void saveWish() {
        Wishes newWish = wishService.saveWish(Wishes.fromDTO(wishSaveRequest));
        assertNotNull(newWish);
    }

    // 카테고리 예외 테스트
    @Test
    public void saveInvalidCategoryWish(){
        wishSaveRequest.setCategoryName("모르겠는데요");
        assertThrows(InvalidCategoryWishException.class, () -> {
            wishService.saveWish(Wishes.fromDTO(wishSaveRequest));
        });
    }

    // soft delete 테스트
    @Test
    public void deleteWish(){
        Long wishId = 14L; // 존재하는 wishId로 변경
        wishService.wishDelete(wishId);

        Wishes wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));

        assertTrue(wish.isDeleted_at()); // true로 저장되었는지 확인

    }

    // status update 테스트
    @Test
    public void updateStatus(){
        Long wishId = 22L; // 존재하는 wishId로 변경
        wishService.updateWishStatus(wishId, "승인됨");

        Wishes wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));

        assertEquals(Status.REJECTED, wish.getStatus()); // REJECTED로 변경되었는지 확인
    }

    // status update 실패 테스트
    @Test
    public void updateInvalidStatus(){
        Long wishId = 4L; // 존재하는 wishId로 변경
        assertThrows(InvalidStatusWishException.class, () -> {
            wishService.updateWishStatus(wishId, "모르겠음");
        });
    }

    // 소원 단일 조회 성공 테스트
    @Test
    public void getSingleWish(){
        Long wishId = 13L; // 존재하는 wishId로 변경
        WishDetailResponse wish = wishService.getWish(wishId);
        System.out.println(wish.getTitle());
        System.out.println(wish.getContent());
        System.out.println(wish.getCategory());
        Wishes checkWish = wishRepository.findById(wishId).orElseThrow();
        assertEquals(checkWish.getTitle(), wish.getTitle());
        assertEquals(checkWish.getContent(), wish.getContent());
        assertEquals(checkWish.getCategory().getKoreanName(), wish.getCategory());
    }

    // 소원 단일 조회 실패 테스트
    @Test
    public void failGetSingleWish(){
        Long wishId = 14L; // 존재하는 wishId로 변경
        assertThrows(UnApprovedStatusException.class, () -> {
            wishService.getWish(wishId);
        });
    }

}