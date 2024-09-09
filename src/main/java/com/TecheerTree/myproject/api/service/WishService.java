package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.request.WishSearchRequest;
import com.TecheerTree.myproject.domain.dto.response.WishDetailResponse;
import com.TecheerTree.myproject.domain.dto.response.WishResponse;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wish;
import com.TecheerTree.myproject.global.exception.InvalidCategoryWishException;
import com.TecheerTree.myproject.global.exception.InvalidStatusWishException;
import com.TecheerTree.myproject.global.exception.UnApprovedStatusException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    @Transactional
    public Wish saveWish(Wish newWish) {
        // DTO에서 Category 변환
        Category category = newWish.getCategory();
        if (category == null) {
            throw new InvalidCategoryWishException("유효하지 않은 카테고리입니다.");
        }

        return wishRepository.save(newWish);
    }

    @Transactional
    public void wishDelete(Long wishId) {
        // soft delete이기에 실제로 삭제 X
        Wish findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));
        // deleted_at만 true로 변경
        findWish.setDeletedAt(true);

        wishRepository.save(findWish);
    }

    @Transactional
    public Wish updateWishStatus(Long wishId, String description) {
        // 넘겨받은 문자열이 유효한 승인상태인지 체크
        Status status = Status.fromDescription(description);
        if (status == null) {
            throw new InvalidStatusWishException("유효하지 않은 승인상태 입니다.");
        }

        Wish findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));
        findWish.setStatus(status);

        return wishRepository.save(findWish);

    }

    public Wish getWish(Long wishId) {
        Wish findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));

        if(findWish.getStatus() != Status.APPROVED){
            throw new UnApprovedStatusException("승인된 소원이 아닙니다.");
        }

        return findWish;
    }

    public Page<Wish> getWishes(Status status, Pageable pageable){
        if (status != null) {
            return wishRepository.findActiveWishByStatus(status, pageable);
        } else {
            return wishRepository.findAllActive(pageable);
        }
    }

    public List<Wish> searchWishes(WishSearchRequest wishSearchRequest) {
        // 카테고리 + 키워드 포함 검색
        if(wishSearchRequest.getCategoryName() != null && wishSearchRequest.getKeyword() != null){
           Category category = Category.fromKoreanName(wishSearchRequest.getCategoryName());
            System.out.println(category);
            return wishRepository.findByCategoryAndKeyword(category, wishSearchRequest.getKeyword());
        }

        // 키워드만 있다면 키워드 검색
        if (wishSearchRequest.getKeyword() != null) {
            return wishRepository.findByKeyword(wishSearchRequest.getKeyword());
        }

        // 카테고리만 있다면 카테고리 검색
        if (wishSearchRequest.getCategoryName() != null) {
            Category category = Category.fromKoreanName(wishSearchRequest.getCategoryName());
            System.out.println(category);

            return wishRepository.findByCategory(category);
        }

        // 모든 조건이 비어 있는 경우 전체 검색
        return wishRepository.findAll();

    }

}
