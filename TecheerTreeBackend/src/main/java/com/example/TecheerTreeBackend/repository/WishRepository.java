package com.example.TecheerTreeBackend.repository;


import com.example.TecheerTreeBackend.domain.Wishes;
import com.example.TecheerTreeBackend.domain.WishStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishRepository extends JpaRepository<Wishes, Long> {

    @Query(value = "SELECT * FROM wishes WHERE  ((:wishStatus = 'APPROVED' AND wish_status = 'APPROVED') AND is_deleted = FALSE) OR ((:wishStatus != 'APPROVED' AND wish_status != 'APPROVED') AND is_deleted = FALSE)  ORDER BY created_at DESC", nativeQuery = true)
    List<Wishes> findByWishStatus(WishStatus wishStatus);
}
