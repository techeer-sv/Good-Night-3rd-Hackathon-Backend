package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Wishes;
import com.example.TecheerTreeBackend.domain.WishStatus;
import com.example.TecheerTreeBackend.dto.WishRequest;
import com.example.TecheerTreeBackend.dto.WishListResponse;
import com.example.TecheerTreeBackend.dto.WishResponse;
import com.example.TecheerTreeBackend.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public String createWish(WishRequest wishRequest) {
        // 소원 엔티티 생성
        Wishes wishes = Wishes.from(wishRequest);

        // 생성 된 소원 엔티티를 레포지토리 DB에 저장
        wishRepository.save(wishes);

        // 잘 저장이 되었다고 반환
        return "저장 성공!";
    }

    public String deleteWish(Long wishId){
        // 소원 조회
        Wishes wishes = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));

        // soft delete 처리
        wishes.softDelete();

        // 처리 후 DB 저장
        wishRepository.save(wishes);

        return "soft delete 처리가 되었습니다.";

    }

    public String confirmWish(Long wishId, WishStatus wishStatus) {
        // 소원 조회
        Wishes wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));

        // 승인 여부 결정
        wish.confirm(wishStatus);

        // 처리후 DB 저장
        wishRepository.save(wish);

        return wish.getWishStatus().getDescription() + " 처리가 되었습니다.";
    }

    public WishResponse viewService(Long wishId) {
        // 소원 조회
        Wishes wishes = wishRepository.findById(wishId)
                .filter(wish -> !wish.getIsDeleted())
                .orElseThrow(() -> new IllegalArgumentException("해당 wishId의 소원을 찾을 수 없습니다."));

        // 승인 처리가 되었다면, 정상 반환
        if (wishes.checkConfirm()) {
            return WishResponse.createWishDto(wishes);
        } else { // 이 외의 경우 null 반환
            return null;
        }
    }

    public List<WishListResponse> viewWishList(WishStatus wishStatus) {
        // 해당 상태에 따른 소원 리스트 가져오기
        List<Wishes> wishes = wishRepository.findByWishStatus(wishStatus);

        // 엔티티 -> dto 변환
        List<WishListResponse> dtos = new ArrayList<WishListResponse>();
        for (int i = 0; i < wishes.size(); i++) {
            Wishes w = wishes.get(i);
            WishListResponse dto = WishListResponse.createWishListDto(w);
            dtos.add(dto);
        }

        return dtos;
    }
}
