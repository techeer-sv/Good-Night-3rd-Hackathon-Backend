package com.example.TecheerTreeBackend.repository;


import com.example.TecheerTreeBackend.domain.Wish;
import com.example.TecheerTreeBackend.domain.WishStatus;
import com.example.TecheerTreeBackend.dto.WishListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query(value = "SELECT * FROM wish WHERE  ((:wishStatus = 'APPROVED' AND is_confirm = 'APPROVED') AND is_deleted = FALSE) OR ((:wishStatus != 'APPROVED' AND is_confirm != 'APPROVED') AND is_deleted = FALSE)  ORDER BY created_at DESC", nativeQuery = true)
    List<Wish> findByWishStatus(WishStatus wishStatus);
}
