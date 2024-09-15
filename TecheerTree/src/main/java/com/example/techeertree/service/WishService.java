package com.example.techeertree.service;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import com.example.techeertree.dto.wish.WishMapper.*;
import com.example.techeertree.dto.wish.WishRequestDto.*;
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

    public WishEntity create(WishCreateRequestDto requestDto){
       WishEntity wish = WishCreateMapper.INSTANCE.toEntity(requestDto);
       wishRepository.save(wish);

       return wish;
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
    public WishEntity update(Long id, Confirm updateConfirm){
        WishEntity wish = wishRepository.findById(id).orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));
        wish.updateIsConfirm(updateConfirm);
        wishRepository.save(wish);

        return wish;
    }

    @Transactional(readOnly = true)
    public WishEntity findOne(Long id){
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

        return wish;
    }

    @Transactional(readOnly = true)
    public Page<WishEntity> findAllWishes(Confirm confirm,Pageable pageable){

     return wishRepository.findByIsConfirmAndIsDeletedFalse(confirm, pageable);

    }

    public List<WishEntity> searchWishes(String keyword, Category category){
        return  wishRepository.findAllWishesWithKeyWord(keyword, category);

    }
}

