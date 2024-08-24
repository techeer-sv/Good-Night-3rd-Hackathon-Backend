package com.example.goodnight3rdhackathonbackend.repository;

import com.example.goodnight3rdhackathonbackend.domain.Wish;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryWishRepository implements WishRepository {

    private static Map<Long, Wish> memory = new HashMap<>();
    private static Long index = 0L;


    @Override
    public void save(Wish wish) {
        wish.setId(++index);
        memory.put(index, wish);

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
    public List<Wish> findAll() {
        return memory.values().stream().toList();
    }


}
