package goodnight.tree.service;

import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.WishSaveRequest;
import goodnight.tree.domain.dto.request.WishUpdateRequest;
import goodnight.tree.domain.dto.response.WishDetailResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service @Transactional
public class WishService {

    @Autowired
    private final WishRepository wishRepository;

    // 소원 등록
    public void saveWishes(WishSaveRequest request) {
        Wish wish = request.toEntity();
        wishRepository.save(wish);

    }

    // 소원 삭제(소프트)
    public void deleteWish(Long wishId) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("Wish not found"));
        wishRepository.delete(wish);  // Soft delete occurs here
    }

    // 소원 승인/거절
    public void updateWish(WishUpdateRequest request, Long wishId) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("Wish not found"));

        wish.changeStatus(request.getStatus()); // 제약 없이 상태 변경
        wishRepository.save(wish);
    }

    // 소원 개별 조회
    public WishDetailResponse findWish(Long wishId) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("Wish not found"));

        if (wish.getIsConfirm() != Wish.WishStatus.APPROVED) {
            throw new RuntimeException("Only approved wishes can be viewed.");
        }

        return WishDetailResponse.from(wish);
    }

    // 소원 리스트 조회(페이지네이션)
    public List<WishResponse> findWishList(int page, int pageSize, Wish.WishStatus status) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("createdAt")));
        Page<Wish> wishes = wishRepository.findAllByIsConfirm(status, pageable);
        return wishes.stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());

    }

    // 소원 키워드 조회
    public List<WishResponse> searchWishes(Wish.Category category, String keyword, Wish.WishStatus status) {
        List<Wish> wishes = wishRepository.searchWishes(category, keyword, status);
        return wishes.stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());
    }

}
