package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Confirm;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import com.TecheerTree.myproject.exception.InvalidWishException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;

    public Wishes saveWish(WishCreateDto wishCreateDto) {
        // DTO에서 Category 변환
        Category category = wishCreateDto.getCategory();
        if (category == null) {
            throw new InvalidWishException("유효하지 않은 카테고리입니다.");
        }

        Wishes newWish = new Wishes();

        newWish.setTitle(wishCreateDto.getTitle());
        newWish.setCategory(wishCreateDto.getCategory());
        newWish.setContent(wishCreateDto.getContent());
        newWish.setIs_confirm(Confirm.PENDING);
        newWish.setCreatedDate();

        return wishRepository.save(newWish);
    }
}
