package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest.WishCreateRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {

  private final WishRepository wishRepository;

  public WishService(WishRepository wishRepository) {
    this.wishRepository = wishRepository;
  }

  public Wish createWish(WishRequest.WishCreateRequest request) {
    Wish wish = request.toEntity();
    return wishRepository.save(wish);
  }

  public void deleteWish(Long id) {
    Wish wish = wishRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Wish not found with id " + id));
    wish.setIsDeleted(true); // soft delete
    wishRepository.save(wish); // 변경 사항 저장
  }

  public Wish updateWishConfirmation(Long id, boolean isConfirmed) {
    Wish wish = wishRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Wish not found with id " + id));

    wish.setIsConfirmed(isConfirmed);  // 업데이트 수행
    return wishRepository.save(wish);  // 업데이트 후 저장
  }
}
