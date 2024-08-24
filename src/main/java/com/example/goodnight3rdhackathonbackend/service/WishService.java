package com.example.goodnight3rdhackathonbackend.service;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import com.example.goodnight3rdhackathonbackend.domain.WishConfirmState;
import com.example.goodnight3rdhackathonbackend.dto.WishDto;
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

    public void saveWish(WishDto.SaveDto wishDto) {
        Wish wish = new Wish();
        wish.setTitle(wishDto.getTitle());
        wish.setContent(wishDto.getContent());
        wish.setCategory(wishDto.getCategory());
        wish.setCreated_at(LocalDate.now());
        wishRepository.save(wish);
    }

    public void deleteWishById(Long id) {
        Wish targetWish = wishRepository.findById(id);
        targetWish.set_deleted(true);
        wishRepository.updateById(id, targetWish);
    }

    public void confirmWishById(Long id, WishDto.ConfirmDto wishDto) {
        Wish targetWish = wishRepository.findById(id);
        targetWish.setIs_confirm(wishDto.getIs_confirm());
        wishRepository.updateById(id, targetWish);
    }

    public WishDto.FindDto findWishById(Long id) {
        Wish targetWish = wishRepository.findById(id);
        if (targetWish.is_deleted()) {
            throw new NotFoundWishException(ErrorCode.NOT_EXIST_WISH);
        }
        if (targetWish.getIs_confirm() != WishConfirmState.APPROVED.getKorean()) {
            throw new NotApprovedWishException(ErrorCode.NOT_APPROVED_WISH);
        }


        WishDto.FindDto wishDto = new WishDto.FindDto();
        wishDto.setTitle(targetWish.getTitle());
        wishDto.setContent(targetWish.getContent());
        wishDto.setCategory(targetWish.getCategory());

        return wishDto;
    }

    public List<WishDto.FindAllDto> findAllWish(String wishState, int page) {
        Pageable pageable = PageRequest.of(page, 2);
        return wishRepository.findAll(pageable)
                .stream()
                .filter(wish -> {
                    if(wishState == WishConfirmState.APPROVED.getEnglish()) {
                        return WishConfirmState.APPROVED.getKorean().equals(wish.getIs_confirm());
                    }
                    if(wishState == WishConfirmState.PENDING.getEnglish()) {
                        return WishConfirmState.PENDING.getKorean().equals(wish.getIs_confirm());
                    }
                    if(wishState == WishConfirmState.REJECTED.getEnglish()) {
                        return WishConfirmState.REJECTED.getKorean().equals(wish.getIs_confirm());
                    }
                    return true;
                })
                .map(wish -> {
                    WishDto.FindAllDto wishDto = new WishDto.FindAllDto();
                    wishDto.setTitle(wish.getTitle());
                    wishDto.setCategory(wish.getCategory());
                    wishDto.setCreated_at(wish.getCreated_at());
                    return wishDto;
                })
                .toList();
    }

}