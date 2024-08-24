package com.example.techeertree.service;

import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.wish.WishMapper.*;
import com.example.techeertree.dto.wish.WishRequestDto;
import com.example.techeertree.dto.wish.WishResponseDto.*;
import com.example.techeertree.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public WishInfoResponseDto create(WishRequestDto requestDto){
       Wish wish = WishCreateMapper.INSTANCE.toEntity(requestDto);
       wishRepository.save(wish);

       return WishCreateMapper.INSTANCE.toDto(wish);
    }

    @Transactional
    public void softDelete(Long id){
        Wish wish = wishRepository.findById(id).orElseThrow(()-> new RuntimeException("Wish not found"));
        wish.setDeleted();
        wishRepository.save(wish);
    }

    public List<Wish> getPendingWishes() {
        return wishRepository.findByIsConfirmAndIsDeletedFalse(Confirm.PENDING);
    }

    @Transactional
    public WishUpdateResponseDto update(Long id, Confirm updateConfirm){
        Wish wish = wishRepository.findById(id).orElseThrow(()-> new RuntimeException("Wish not found"));

        wish.updateIsConfirm(updateConfirm);
        wishRepository.save(wish);

        return WishUpdateMapper.INSTANCE.toDto(wish);
        // 소원 승인/거절
        //모든 소원에 대한 승인/거절을 수행합니다.
        //보류됨 상태의 소원을 조회하여 각각의 소원에 대한 승인/거절을 진행합니다.
    }



}

