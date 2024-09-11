package com.example.techeertree.repository;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishEntityRepository extends JpaRepository<WishEntity, Long> {

    Optional<WishEntity> findById(Long id);

    List<WishEntity> findByIsConfirmAndIsDeletedFalse(Confirm isconfirm);

    Page<WishEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    //사용자가 입력한 키워드를 기반으로, 소원의 제목이나 내용에서 일치하는 항목을 검색해 반환합니다.
    // 이 기능은 카테고리 필터와 함께 사용할 수 있습니다.
    @Query("SELECT w FROM Wish w WHERE (w.title LIKE %:keyword% OR w.content LIKE %:keyword%) " +
            "AND (:category IS NULL OR w.category = :category ) " +
            "AND w.isDeleted = false")
    List<WishEntity> findAllWishesWithKeyWord(@Param("keyword") String keyword, @Param("category") Category category);
}
