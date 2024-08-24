package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.entitiy.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wishes, Long> {
}
