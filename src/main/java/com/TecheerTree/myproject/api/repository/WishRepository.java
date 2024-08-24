package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.dto.ReturnAllWishDto;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wishes, Long> {

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false AND (:status IS NULL OR w.status = :status)")
    Page<Wishes> findActiveWishesByStatus(@Param("status") Status status, Pageable pageable);

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false")
    Page<Wishes> findAllActive(Pageable pageable);
}
