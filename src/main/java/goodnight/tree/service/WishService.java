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

}
