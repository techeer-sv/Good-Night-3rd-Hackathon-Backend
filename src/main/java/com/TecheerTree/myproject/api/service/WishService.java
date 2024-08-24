package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.WishRepository;
import com.TecheerTree.myproject.domain.dto.ReturnSingleWishDto;
import com.TecheerTree.myproject.domain.dto.WishCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import com.TecheerTree.myproject.exception.InvalidCategoryWishException;
import com.TecheerTree.myproject.exception.InvalidStatusWishException;
import com.TecheerTree.myproject.exception.UnApprovedStatusException;
import jakarta.persistence.EntityNotFoundException;
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
            throw new InvalidCategoryWishException("유효하지 않은 카테고리입니다.");
        }

        Wishes newWish = new Wishes();

        newWish.setTitle(wishCreateDto.getTitle());
        newWish.setCategory(wishCreateDto.getCategory());
        newWish.setContent(wishCreateDto.getContent());
        newWish.setIs_confirm(Status.PENDING);
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

    public Wishes updateWishStatus(Long wishId, String description) {
        // 넘겨받은 문자열이 유효한 승인상태인지 체크
        Status status = Status.fromDescription(description);
        if (status == null) {
            throw new InvalidStatusWishException("유효하지 않은 승인상태 입니다.");
        }

        Wishes findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));
        findWish.setIs_confirm(status);

        return wishRepository.save(findWish);

    }

    public ReturnSingleWishDto getWish(Long wishId) {
        Wishes findWish = wishRepository.findById(wishId).orElseThrow(()
                -> new EntityNotFoundException("Wish not found with id: " + wishId));

        if(findWish.getIs_confirm() != Status.APPROVED){
            throw new UnApprovedStatusException("승인된 소원이 아닙니다.");
        }

        ReturnSingleWishDto findWishDto = new ReturnSingleWishDto();
        findWishDto.setTitle(findWish.getTitle());
        findWishDto.setContent(findWish.getContent());
        findWishDto.setCategory(findWish.getCategory().getKoreanName());

        return findWishDto;
    }
}
