package goodnight.tree.service;

import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.WishSaveRequest;
import goodnight.tree.domain.dto.request.WishUpdateRequest;
import goodnight.tree.domain.dto.response.WishDetailResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.repository.WishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
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

    private final WishRepository wishRepository;

    // 소원 등록
    public void saveWishes(WishSaveRequest request) {
        Wish wish = request.toEntity();
        wishRepository.save(wish);

    }

    // 소원 삭제(소프트)
    public void deleteWish(Long wishId) {
        Wish wish = wishRepository.findByIdAndDeletedAtIsNull(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found"));
        wish.softDelete();  // Soft delete occurs here
    }

    // 소원 승인/거절
    public void updateWish(WishUpdateRequest request, Long wishId) {
        Wish wish = wishRepository.findByIdAndDeletedAtIsNull(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found"));

        wish.changeStatus(request.getStatus()); // 제약 없이 상태 변경
    }

    // 소원 개별 조회
    @Transactional(readOnly=true)
    public WishDetailResponse findWish(Long wishId) {
        Wish wish = wishRepository.findByIdAndDeletedAtIsNull(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish not found"));

        if (wish.getWishStatus() != Wish.WishStatus.APPROVED) {
            throw new IllegalStateException("Only approved wishes can be viewed.");
        }

        return WishDetailResponse.from(wish);
    }

    // 소원 리스트 조회(페이지네이션)
    @Transactional(readOnly = true)
    public List<WishResponse> findWishList(Pageable pageable, Wish.WishStatus status) {
        Page<Wish> wishes = wishRepository.findAllByWishStatusAndDeletedAtIsNull(status, pageable);
        return wishes.stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());

    }

    // 소원 키워드 조회
    @Transactional(readOnly = true)
    public List<WishResponse> searchWishes(Wish.Category category, String keyword, Wish.WishStatus status) {
        List<Wish> wishes = wishRepository.searchWishesAndDeletedAtIsNull(category, keyword, status);
        return wishes.stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());
    }

}
