package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wishes, Long> {

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false AND (:status IS NULL OR w.status = :status)")
    Page<Wishes> findActiveWishesByStatus(@Param("status") Status status, Pageable pageable);

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false")
    Page<Wishes> findAllActive(Pageable pageable);

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false AND w.category = :category AND (w.title LIKE %:keyword% OR w.content LIKE %:keyword%)")
    List<Wishes> findByCategoryAndKeyword(@Param("category") Category category, @Param("keyword") String keyword);

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false AND w.category = :category")
    List<Wishes> findByCategory(@Param("category") Category category);

    @Query("SELECT w FROM Wishes w WHERE w.deleted_at = false AND (w.title LIKE %:keyword% OR w.content LIKE %:keyword%)")
    List<Wishes> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT w FROM Wishes w WHERE w.status = :status AND w.deleted_at = false")
    List<Wishes> findByConfirmStatus(@Param("status") Status status);
}
