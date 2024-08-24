package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.WishesDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository.WishesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


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
        wish.setCreatedAt(LocalDate.now());
        wish.setIsConfirm(Wishes.WishesStatus.PENDING);

        return wishesRepository.save(wish);
    }

    @Transactional
    public void deleteWish(Long id) {
        Wishes wish = wishesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));
        wish.setIsDeleted(true);
        wishesRepository.save(wish);
    }
}