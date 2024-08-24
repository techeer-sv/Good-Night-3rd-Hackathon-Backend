package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Wish;
import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;


    public String createWish(WishForm wishForm) {
        // 소원 엔티티 생성
        Wish wish  = Wish.createWish(wishForm);

        // 생성 된 소원 엔티티를 레포지토리 DB에 저장
        wishRepository.save(wish);

        // 잘 저장이 되었다고 반환
        return "저장 성공!";
    }
}
