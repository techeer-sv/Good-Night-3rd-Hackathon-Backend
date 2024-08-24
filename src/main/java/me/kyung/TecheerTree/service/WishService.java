package me.kyung.TecheerTree.service;

import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public Wish createWish(Wish wish) {
        return wishRepository.save(wish);
    }
}