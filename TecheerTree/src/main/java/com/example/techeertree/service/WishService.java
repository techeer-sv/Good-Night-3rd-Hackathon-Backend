package com.example.techeertree.service;

import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.wish.WishMapper;
import com.example.techeertree.dto.wish.WishRequestDto;
import com.example.techeertree.dto.wish.WishResponseDto;
import com.example.techeertree.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public WishResponseDto create(WishRequestDto requestDto){
       Wish wish = WishMapper.INSTANCE.toEntity(requestDto);
       wishRepository.save(wish);

       return WishMapper.INSTANCE.toDto(wish);
    }


    @Transactional
    public void softDelete(Long id){
        Wish wish = wishRepository.findById(id).orElseThrow(()-> new RuntimeException("Wish not found"));
        wish.setDeleted();
        wishRepository.save(wish);
    }


}

