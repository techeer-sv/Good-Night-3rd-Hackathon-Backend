package com.example.goodnight3rdhackathonbackend.service;

import com.example.goodnight3rdhackathonbackend.dto.WishDto;
import com.example.goodnight3rdhackathonbackend.repository.WishRepository;
import org.springframework.stereotype.Service;


@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void saveWish(WishDto wishDto) {
    }

}
