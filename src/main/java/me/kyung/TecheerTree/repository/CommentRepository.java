package me.kyung.TecheerTree.repository;

import me.kyung.TecheerTree.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}