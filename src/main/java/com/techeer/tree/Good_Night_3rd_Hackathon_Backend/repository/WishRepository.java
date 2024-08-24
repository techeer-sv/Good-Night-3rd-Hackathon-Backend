package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByIsConfirmed(Boolean isConfirmed);
    List<Wish> findByCategoryAndIsConfirmed(String category, Boolean isConfirmed);
    List<Wish> findByCategoryAndIsConfirmedIsNull(String category);
    List<Wish> findByIsConfirmedIsNull();

    List<Wish> findByTitleContainingAndIsConfirmedTrue(String keyword);
}

