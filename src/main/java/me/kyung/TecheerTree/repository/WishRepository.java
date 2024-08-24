package me.kyung.TecheerTree.repository;

import me.kyung.TecheerTree.domain.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query("SELECT w FROM Wish w WHERE w.isConfirm = :status ORDER BY w.createdDate DESC")
    Page<Wish> findByIsConfirmDesc(Wish.Status status, Pageable pageable);
}