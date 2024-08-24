package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.dto.ReturnAllWishDto;
import com.TecheerTree.myproject.domain.entitiy.Status;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wishes, Long> {
    Page<Wishes> findByStatus(Status status, Pageable pageable);
}
