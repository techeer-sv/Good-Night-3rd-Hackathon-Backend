package com.example.Good_Night_3rd_Hackathon_Backend.service;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.dto.WishesListDto;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;

@Transactional
public class WishesService {
    private final WishesRepository wishesRepository;

    public WishesService(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }

    public Long createWish(Wishes wish) {
        return wishesRepository.save(wish).getId();
    }

    public void deleteWish(Long id) {
        Optional<Wishes> wish = wishesRepository.findById(id);
        if (wish.isPresent()) {
            Wishes foundWish = wish.get();
            foundWish.setDeleted(true);  // 소프트 삭제 플래그 설정
            wishesRepository.save(foundWish);  // 엔티티 업데이트
        } else {
            throw new EntityNotFoundException("Wish not found with id: " + id);
        }
    }

    public void confirmWish(Long id, ConfirmStatus confirmStatus) {
        wishesRepository.findById(id).ifPresent(wish -> {
            wish.setIsConfirmed(confirmStatus);
            wishesRepository.save(wish);
        });
    }

    public Optional<Wishes> getWish(Long id) {
        return wishesRepository.findByIdAndIsDeletedFalseAndIsConfirmed(id, ConfirmStatus.CONFIRMED);
    }

    public Page<WishesListDto> getWishesByStatus(ConfirmStatus is_confirmed, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Wishes> wishes = wishesRepository.findAllByIsDeletedFalseAndIsConfirmed(is_confirmed, pageable);
        return wishes.map(WishesListDto::new);
    }

    public Page<WishesListDto> searchWishes(String keyword, String category, ConfirmStatus isConfirmed, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Wishes> wishes = wishesRepository.searchWishes(keyword, category, isConfirmed, pageable);
        return wishes.map(WishesListDto::new);
    }

}
