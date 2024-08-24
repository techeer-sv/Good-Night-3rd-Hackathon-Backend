package com.example.Good_Night_3rd_Hackathon_Backend.service;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import jakarta.transaction.Transactional;

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
        wishesRepository.deleteById(id);
    }

    public void confirmWish(Long id, ConfirmStatus confirmStatus) {
        wishesRepository.findById(id).ifPresent(wish -> {
            wish.setIs_confirmed(confirmStatus);
            wishesRepository.save(wish);
        });
    }
}
