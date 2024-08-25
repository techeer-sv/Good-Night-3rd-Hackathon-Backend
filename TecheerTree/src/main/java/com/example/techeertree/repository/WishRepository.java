package com.example.techeertree.repository;

import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findAll();
    Optional<Wish> findById(Long id);

    List<Wish> findByIsConfirmAndIsDeletedFalse(Confirm isconfirm);

    Page<Wish> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
