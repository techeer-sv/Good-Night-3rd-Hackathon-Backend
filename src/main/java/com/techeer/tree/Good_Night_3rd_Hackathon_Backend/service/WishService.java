package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest.WishCreateRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.CommentResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.CommentResponse.CommentListResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.WishResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.WishResponse.WishReadListResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.CommentRepository;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.WishRepository;
import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class WishService {

  private final WishRepository wishRepository;

  public WishService(WishRepository wishRepository) {
    this.wishRepository = wishRepository;
  }

  public Wish createWish(WishCreateRequest request) {
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

  public Wish readDetailWish(Long id) {
    return wishRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Wish not found with id " + id));
  }

  @Transactional(readOnly = true)
  public List<WishReadListResponse> getAllWishes(String category, Boolean isConfirmed) {
    List<Wish> wishes;
    if (category != null && isConfirmed != null) {
      wishes = wishRepository.findByCategoryAndIsConfirmed(category, isConfirmed);
    } else if (category != null && isConfirmed == null) {
      wishes = wishRepository.findByCategoryAndIsConfirmedIsNull(category);
    } else if (isConfirmed != null) {
      wishes = wishRepository.findByIsConfirmed(isConfirmed);
    } else if (isConfirmed == null) {
      wishes = wishRepository.findByIsConfirmedIsNull();
    } else {
      wishes = wishRepository.findAll();
    }

    return wishes.stream()
        .map(WishReadListResponse::from)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<WishResponse.WishSearchResponse> searchWishesByTitle(String keyword) {
    List<Wish> wishes = wishRepository.findByTitleContainingAndIsConfirmedTrue(keyword);
    return wishes.stream()
        .map(WishResponse.WishSearchResponse::from)
        .collect(Collectors.toList());
  }
}
