package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Confirm;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import com.TecheerTree.myproject.exception.InvalidWishException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public void wishDelete(Long wishId) {
        // soft delete이기에 실제로 삭제 X
        Wishes findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));

        // deleted_at만 true로 변경
        findWish.setDeleted_at(true);

        wishRepository.save(findWish);
    }
}
