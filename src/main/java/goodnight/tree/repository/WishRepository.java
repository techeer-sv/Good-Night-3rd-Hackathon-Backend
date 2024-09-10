package goodnight.tree.repository;

import goodnight.tree.domain.dao.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    // Custom query methods can be added here
    Page<Wish> findAllByWishStatusAndDeletedAtIsNull(Wish.WishStatus status, Pageable pageable);
    // 카테고리와 키워드를 이용한 검색
    @Query("SELECT w FROM Wish w WHERE w.deletedAt IS NULL " +
            "AND (:category IS NULL OR w.category = :category) " +
            "AND (w.title LIKE %:keyword% OR w.content LIKE %:keyword%) " +
            "AND (:status IS NULL OR w.wishStatus = :status)")
    List<Wish> searchWishesAndDeletedAtIsNull(@Param("category") Wish.Category category,
                            @Param("keyword") String keyword,
                            @Param("status") Wish.WishStatus status);

    Optional<Wish> findByIdAndDeletedAtIsNull(Long wishId);
}
