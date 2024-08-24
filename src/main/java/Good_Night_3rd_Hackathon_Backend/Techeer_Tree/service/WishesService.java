package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.WishesDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository.WishesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class WishesService {

    private final WishesRepository wishesRepository;

    public WishesService(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }

    @Transactional
    public Wishes createWish(WishesDto wishesDto) {
        if (wishesDto.getTitle() == null || wishesDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("소원 제목을 입력해주세요.");
        }
        else if (wishesDto.getContent() == null || wishesDto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("소원 내용을 입력해주세요.");
        }
        else if (wishesDto.getCategory() == null) {
            throw new IllegalArgumentException("소원 카테고리를 선택해주세요.");
        }


        Wishes wish = new Wishes();
        wish.setTitle(wishesDto.getTitle());
        wish.setContent(wishesDto.getContent());
        wish.setCategory(wishesDto.getCategory());
        wish.setCreatedAt(LocalDateTime.now());
        wish.setIsConfirm(Wishes.WishesStatus.PENDING);

        return wishesRepository.save(wish);
    }

    @Transactional
    public void deleteWish(Long id) {
        Wishes wish = wishesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));
        wish.setIsDeleted(true);
        wishesRepository.save(wish);
    }

    @Transactional
    public void approveWish(Long id, Wishes.WishesStatus status) {
        Wishes wish = wishesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));
        if (wish.getIsConfirm() == Wishes.WishesStatus.PENDING) {
            wish.setIsConfirm(status);
            wishesRepository.save(wish);
        }
    }

    @Transactional
    public Wishes getWish(Long id) {
        Wishes wish = wishesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));
        if (wish.getIsDeleted()) {
            throw new IllegalArgumentException("삭제된 소원입니다.");
        }
        if (wish.getIsConfirm() != Wishes.WishesStatus.APPROVED) {
            throw new IllegalArgumentException("승인되지 않은 소원입니다.");
        }
        return wish;
    }

    @Transactional
    public Page<Wishes> getWishList(Wishes.WishesStatus status, Pageable pageable) {
        return wishesRepository.getWishList(status, pageable);
    }
}

