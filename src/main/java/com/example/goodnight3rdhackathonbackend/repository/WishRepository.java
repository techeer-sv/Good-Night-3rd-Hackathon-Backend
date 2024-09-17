package com.example.goodnight3rdhackathonbackend.repository;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository {

    void save(Wish wish);
    void updateById(Long id, Wish wish);

    Wish findById(Long id);
    List<Wish> findAll(Pageable pageable);


}
