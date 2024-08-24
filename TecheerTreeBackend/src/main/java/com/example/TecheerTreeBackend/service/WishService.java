package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Wish;
import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import com.example.TecheerTreeBackend.dto.WishResponse;
import com.example.TecheerTreeBackend.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public String deleteWish(Long wishId){
        // 소원 조회
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));

        // soft delete 처리
        wish.softDelete();

        // 처리 후 DB 저장
        wishRepository.save(wish);

        return "soft delete 처리가 되었습니다.";

    }

    public String confirmWish(Long wishId, WishConfirmForm wishConfirmForm) {
        // 소원 조회
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));

        // 승인 여부 결정
        String result = wish.confirm(wishConfirmForm);

        // 처리후 DB 저장
        wishRepository.save(wish);

        return result + " 처리가 되었습니다.";
    }

    public WishResponse viewService(Long wishId) {
        // 소원 조회
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));
        // 승인 처리 확인
        Boolean check = wish.checkConfirm();

        // 승인 처리가 되었다면 정상 반환 되지 않았다면 null 반환
        if (check == Boolean.TRUE) {
            return WishResponse.createWishDto(wish);
        }else {
            return null;
        }
    }
}
