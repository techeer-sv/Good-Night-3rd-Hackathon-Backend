package com.example.Good_Night_3rd_Hackathon_Backend.repository;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface WishesRepository extends JpaRepository<Wishes, Long> {
    Optional<Wishes> findByIdAndIsDeletedFalseAndIsConfirmed(Long id, ConfirmStatus is_confirmed);
    Page<Wishes> findAllByIsDeletedFalseAndIsConfirmed(ConfirmStatus is_confirmed, Pageable pageable);
    @Query("SELECT w FROM Wishes w WHERE " +
            "w.isDeleted = false AND " +
            "(LOWER(w.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(w.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:category IS NULL OR w.category = :category) AND " +
            "w.isConfirmed = :isConfirmed")
    Page<Wishes> searchWishes(@Param("keyword") String keyword,
                              @Param("category") String category,
                              @Param("isConfirmed") ConfirmStatus isConfirmed
                              , Pageable pageable);
}
