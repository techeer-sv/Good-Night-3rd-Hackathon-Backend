package com.example.techeertree.repository;

import com.example.techeertree.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findAll();
    Optional<Wish> findById(Long id);

    @Query("SELECT w FROM Wish w WHERE w.isDeleted = false")
    List<Wish> findAllActiveWishes();

}
