package me.kyung.TecheerTree.repository;

import me.kyung.TecheerTree.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.wish.id = :wishId ORDER BY c.createdDate ASC")
    Page<Comment> findByWishIdAsc(Long wishId,Pageable pageable);
}