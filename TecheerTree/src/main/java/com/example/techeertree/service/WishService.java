package com.example.techeertree.service;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import com.example.techeertree.dto.wish.WishMapper.*;
import com.example.techeertree.dto.wish.WishRequestDto.*;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.exception.BaseException;
import com.example.techeertree.exception.ErrorCode;
import com.example.techeertree.repository.WishEntityRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishEntityRepository wishRepository;

    public WishInfoResponseDto create(WishCreateRequestDto requestDto){
       WishEntity wish = WishCreateMapper.INSTANCE.toEntity(requestDto);
       wishRepository.save(wish);

       return WishCreateMapper.INSTANCE.toDto(wish);
    }

    @Transactional
    public void softDelete(Long id){
        WishEntity wish = wishRepository.findById(id).orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));
        wish.setDeleted();
        wishRepository.save(wish);
    }

    public List<WishEntity> getPendingWishes() {
        return wishRepository.findByIsConfirmAndIsDeletedFalse(Confirm.PENDING);
    }

    @Transactional
    public WishUpdateResponseDto update(Long id, Confirm updateConfirm){
        WishEntity wish = wishRepository.findById(id).orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));
        wish.updateIsConfirm(updateConfirm);
        wishRepository.save(wish);

        return WishUpdateMapper.INSTANCE.toDto(wish);
    }

    @Transactional(readOnly = true)
    public WishInfoResponseDto findOne(Long id){
        WishEntity wish = wishRepository.findById(id)
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

        Page<WishEntity> wishes = wishRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<WishListResponseDto> filteredWishes = wishes.stream()
                .filter(w -> w.getIsConfirm() == confirm )
                .filter(w -> !w.isDeleted())
                .map(WishReadMapper.INSTANCE::toDto)
                .toList();

        return new PageImpl<>(filteredWishes, pageable, wishes.getTotalElements());
    }

    public List<WishInfoResponseDto> searchWishes(String keyword, Category category){
        List<WishEntity> wishes = wishRepository.findAllWishesWithKeyWord(keyword, category);

        List<WishInfoResponseDto> filteredWishes = wishes.stream()
                .map(WishCreateMapper.INSTANCE::toDto).toList();

        return filteredWishes;
    }
}

