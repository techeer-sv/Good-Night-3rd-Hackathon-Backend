package com.example.goodnight3rdhackathonbackend.repository;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryWishRepository implements WishRepository {

    private static Map<Long, Wish> memory = new ConcurrentHashMap<>();
    private static AtomicLong index = new AtomicLong();


    @Override
    public void save(Wish wish) {
        wish.setId(index.incrementAndGet());
        memory.put(index.get(), wish);

    }

    @Override
    public void updateById(Long id, Wish wish) {
        memory.put(id, wish);
    }

    @Override
    public Wish findById(Long id) {
        return memory.get(id);
    }

    @Override
    public List<Wish> findAll(Pageable pageable) {
        return memory.values().stream()
                .sorted(Comparator.comparing(Wish::getId).reversed())
                        .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                        .limit(pageable.getPageSize())
                        .collect(Collectors.toList());
    }


}
