package com.example.techeertree.service;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.wish.WishMapper.*;
import com.example.techeertree.dto.wish.WishRequestDto.*;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.exception.BaseException;
import com.example.techeertree.exception.ErrorCode;
import com.example.techeertree.repository.WishRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public WishInfoResponseDto create(WishCreateRequestDto requestDto){
       Wish wish = WishCreateMapper.INSTANCE.toEntity(requestDto);
       wishRepository.save(wish);

       return WishCreateMapper.INSTANCE.toDto(wish);
    }

    @Transactional
    public void softDelete(Long id){
        Wish wish = wishRepository.findById(id).orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));
        wish.setDeleted();
        wishRepository.save(wish);
    }

    public List<Wish> getPendingWishes() {
        return wishRepository.findByIsConfirmAndIsDeletedFalse(Confirm.PENDING);
    }

    @Transactional
    public WishUpdateResponseDto update(Long id, Confirm updateConfirm){
        Wish wish = wishRepository.findById(id).orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));
        wish.updateIsConfirm(updateConfirm);
        wishRepository.save(wish);

        return WishUpdateMapper.INSTANCE.toDto(wish);
    }

    @Transactional(readOnly = true)
    public WishInfoResponseDto findOne(Long id){
        Wish wish = wishRepository.findById(id)
                .filter(w -> {
                    if(w.getIsConfirm() != Confirm.CONFIRM) {
                        throw new BaseException(ErrorCode.NOT_CONFIRMED);
                    }
                    return true;
                })
                .filter(w -> {
                    if(w.isDeleted()){
                        throw new BaseException(ErrorCode.NOT_EXIST_WISH);
                    }
                    return true;
                })
                .orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));

        return WishCreateMapper.INSTANCE.toDto(wish);
    }

    @Transactional(readOnly = true)
    public Page<WishListResponseDto> findAllWishes(Confirm confirm,int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Wish> wishes = wishRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<WishListResponseDto> filteredWishes = wishes.stream()
                .filter(w -> w.getIsConfirm() == confirm )
                .filter(w -> !w.isDeleted())
                .map(WishReadMapper.INSTANCE::toDto)
                .toList();

        return new PageImpl<>(filteredWishes, pageable, wishes.getTotalElements());
    }

    public List<WishInfoResponseDto> searchWishes(String keyword, Category category){
        List<Wish> wishes = wishRepository.findAllWishesWithKeyWord(keyword, category);

        List<WishInfoResponseDto> filteredWishes = wishes.stream()
                .map(WishCreateMapper.INSTANCE::toDto).toList();

        return filteredWishes;
    }
}

