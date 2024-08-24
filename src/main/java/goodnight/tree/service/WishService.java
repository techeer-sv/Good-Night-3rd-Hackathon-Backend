package goodnight.tree.service;

import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.WishSaveRequest;
import goodnight.tree.domain.dto.request.WishUpdateRequest;
import goodnight.tree.domain.dto.response.WishDetailResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
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


}
