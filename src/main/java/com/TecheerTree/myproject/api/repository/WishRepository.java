package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.entitiy.Category;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("SELECT w FROM Wish w WHERE w.deletedAt = false AND (:status IS NULL OR w.status = :status)")
    Page<Wish> findActiveWishByStatus(@Param("status") Status status, Pageable pageable);

    @Query("SELECT w FROM Wish w WHERE w.deletedAt = false")
    Page<Wish> findAllActive(Pageable pageable);

    @Query("SELECT w FROM Wish w WHERE w.deletedAt = false AND w.category = :category AND (w.title LIKE %:keyword% OR w.content LIKE %:keyword%)")
    List<Wish> findByCategoryAndKeyword(@Param("category") Category category, @Param("keyword") String keyword);

    @Query("SELECT w FROM Wish w WHERE w.deletedAt = false AND w.category = :category")
    List<Wish> findByCategory(@Param("category") Category category);

    @Query("SELECT w FROM Wish w WHERE w.deletedAt = false AND (w.title LIKE %:keyword% OR w.content LIKE %:keyword%)")
    List<Wish> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT w FROM Wish w WHERE w.status = :status AND w.deletedAt = false")
    List<Wish> findByConfirmStatus(@Param("status") Status status);
}
