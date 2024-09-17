package com.example.goodnight3rdhackathonbackend.service;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import com.example.goodnight3rdhackathonbackend.domain.WishConfirmState;
import com.example.goodnight3rdhackathonbackend.dto.WishConfirmRequestDto;
import com.example.goodnight3rdhackathonbackend.dto.WishFindAllResponseDto;
import com.example.goodnight3rdhackathonbackend.dto.WishFindResponseDto;
import com.example.goodnight3rdhackathonbackend.dto.WishSaveRequestDto;
import com.example.goodnight3rdhackathonbackend.error.ErrorCode;
import com.example.goodnight3rdhackathonbackend.error.NotApprovedWishException;
import com.example.goodnight3rdhackathonbackend.error.NotFoundWishException;
import com.example.goodnight3rdhackathonbackend.repository.WishRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;


import java.time.LocalDate;
import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public void deleteWishById(Long id) {
        Wish targetWish = wishRepository.findById(id);
        targetWish.setDeleted(true);
        wishRepository.updateById(id, targetWish);
    }

    public void confirmWishById(Long id, String isConfirm) {
        Wish targetWish = wishRepository.findById(id);
        targetWish.setIsConfirm(isConfirm);
        wishRepository.updateById(id, targetWish);
    }

    public WishFindResponseDto findWishById(Long id) {
        Wish targetWish = wishRepository.findById(id);
        if (targetWish.isDeleted()) {
            throw new NotFoundWishException(ErrorCode.NOT_EXIST_WISH);
        }
        if (targetWish.getIsConfirm() != WishConfirmState.APPROVED.getKorean()) {
            throw new NotApprovedWishException(ErrorCode.NOT_APPROVED_WISH);
        }


        WishFindResponseDto wishDto = new WishFindResponseDto();
        wishDto.setTitle(targetWish.getTitle());
        wishDto.setContent(targetWish.getContent());
        wishDto.setCategory(targetWish.getCategory());

        return wishDto;
    }

    public List<WishFindAllResponseDto> findAllWish(String wishState, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return wishRepository.findAll(pageable)
                .stream()
                .filter(wish -> {
                    if(wishState == WishConfirmState.APPROVED.getEnglish()) {
                        return WishConfirmState.APPROVED.getKorean().equals(wish.getIsConfirm());
                    }
                    if(wishState == WishConfirmState.PENDING.getEnglish()) {
                        return WishConfirmState.PENDING.getKorean().equals(wish.getIsConfirm());
                    }
                    if(wishState == WishConfirmState.REJECTED.getEnglish()) {
                        return WishConfirmState.REJECTED.getKorean().equals(wish.getIsConfirm());
                    }
                    return true;
                })
                .map(wish -> {
                    WishFindAllResponseDto wishDto = new WishFindAllResponseDto();
                    wishDto.setTitle(wish.getTitle());
                    wishDto.setCategory(wish.getCategory());
                    wishDto.setCreatedAt(wish.getCreatedAt());
                    return wishDto;
                })
                .toList();
    }

}