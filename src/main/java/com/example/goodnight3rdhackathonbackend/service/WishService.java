package com.example.goodnight3rdhackathonbackend.service;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import com.example.goodnight3rdhackathonbackend.dto.WishDto;
import com.example.goodnight3rdhackathonbackend.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        targetWish.setDeleted_at(LocalDateTime.now());
        wishRepository.updateById(id, targetWish);
    }

}
