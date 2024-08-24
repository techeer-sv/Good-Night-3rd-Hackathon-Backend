package me.kyung.TecheerTree.repository;

import me.kyung.TecheerTree.domain.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query("SELECT p FROM Wish p ORDER BY p.createdDate DESC")
    Page<Wish> findByIsConfirmDesc(Wish.Status status, Pageable pageable);
}