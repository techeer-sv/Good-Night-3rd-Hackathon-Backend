package com.example.techeertree.service;

import com.example.techeertree.entity.Wish;
import com.example.techeertree.entity.Wish.ApprovalStatus;
import com.example.techeertree.entity.Wish.Category;
import com.example.techeertree.exception.ResourceNotFoundException;
import com.example.techeertree.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    @Autowired
    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // 소원 생성 메서드
    public Wish createWish(String title, String content, Category category) {
        if (title == null || content == null || category == null) {
            throw new IllegalArgumentException("Title, Content and Category cannot be null");
        }

        Wish wish = new Wish();
        wish.setTitle(title);
        wish.setContent(content);
        wish.setCategory(category);
        wish.setCreatedDate(LocalDateTime.now());

        return wishRepository.save(wish);
    }

    // 소원 삭제 메서드 (soft delete)
    public void deleteWish(Long id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wish not found"));

        wish.setIsDeleted(true);
        wishRepository.save(wish);
    }

    // 보류된 소원들을 가져오는 메서드
    public List<Wish> getPendingWishes(Pageable pageable) {
        return wishRepository.findAllByIsDeletedFalseAndIsConfirm(ApprovalStatus.PENDING, pageable).getContent();
    }

    // 소원 승인 메서드
    public void approveWish(Long id) {
        updateWishApprovalStatus(id, ApprovalStatus.APPROVED);
    }

    // 소원 거절 메서드
    public void rejectWish(Long id) {
        updateWishApprovalStatus(id, ApprovalStatus.REJECTED);
    }

    // 소원 상태 업데이트 메서드
    private void updateWishApprovalStatus(Long id, ApprovalStatus status) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wish not found"));

        wish.setIsConfirm(status);
        wishRepository.save(wish);
    }

    // 단일 소원을 조회하는 메서드
    public Wish getWish(Long id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wish not found"));

        if (wish.getIsDeleted() || wish.getIsConfirm() != ApprovalStatus.APPROVED) {
            throw new IllegalStateException("Wish is either deleted or not approved");
        }

        return wish;
    }

    // 소원 목록을 가져오는 메서드 (페이지네이션 지원)
    public Page<Wish> getWishList(ApprovalStatus status, Pageable pageable) {
        if (status == null) {
            return wishRepository.findAllByIsDeletedFalse(pageable);
        } else {
            return wishRepository.findAllByIsDeletedFalseAndIsConfirm(status, pageable);
        }
    }

    // 소원 검색 메서드
    public List<Wish> searchWishes(String keyword, Category category, Pageable pageable) {
        if (category != null) {
            return wishRepository.findByTitleContainingAndContentContainingAndCategoryAndIsDeletedFalse(keyword, keyword, category, pageable);
        } else {
            return wishRepository.findByTitleContainingAndContentContainingAndIsDeletedFalse(keyword, keyword, pageable);
        }
    }
}
