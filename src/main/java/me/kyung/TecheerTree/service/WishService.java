package me.kyung.TecheerTree.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.dto.response.WishDetailResponse;
import me.kyung.TecheerTree.dto.response.WishListResponse;
import me.kyung.TecheerTree.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishService {
    private final WishRepository wishRepository;

    public List<WishListResponse> findAllWish(Wish.Status status, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Wish> wishPage = wishRepository.findByIsConfirmDesc(status, pageable);

        return wishPage.getContent().stream()
                .map(WishListResponse::from)
                .collect(Collectors.toList());
    }

    public Wish createWish(Wish wish) {
        return wishRepository.save(wish);
    }

    public void deleteWish(Long id){
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
        wishRepository.deleteById(id);

    }

    public WishDetailResponse findWish(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow( () -> new RuntimeException("존재하지 않는 게시글입니다."));
        return WishDetailResponse.from(wish);
    }

    public Wish updateWish(Long id, Wish.Status status){
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        wish.setIsConfirm(status);

        return wishRepository.save(wish);
    }
}