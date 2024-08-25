package com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dao;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.entity.Wish;

import java.util.List;

public interface WishDao {
    void createWish(Wish wish); //소원생성

    Wish getWish(Long wishId); //단일 소원 조회

    List<Wish> getWishList(); // 소원리스트 조회

    void deleteWish(Long wishId); // 소원삭제
    void confirmWish(Long id, Boolean confirm); //소원 승인
}
