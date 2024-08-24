package me.kyung.TecheerTree.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishService {
    private final WishRepository wishRepository;


    public Wish createWish(Wish wish) {
        return wishRepository.save(wish);
    }

    public void deleteWish(Long id){
        wishRepository.deleteById(id);
    }

}