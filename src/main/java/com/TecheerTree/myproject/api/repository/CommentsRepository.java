package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.entitiy.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
