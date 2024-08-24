package com.example.Good_Night_3rd_Hackathon_Backend.repository;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.service.WishesService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class WishesRepositoryTest {
    @Autowired
    WishesRepository wishesRepository;
    @Autowired
    WishesService wishesService;

    //특정 ID의 소원을 삭제되지 않은 상태에서 조회할 수 있는지 테스트
    @Test
    public void testFindByIdAndIsDeletedFalseAndConfirmed() {
        //Given
        Wishes wish = new Wishes("title", "content", "category", ConfirmStatus.CONFIRMED);
        wishesRepository.save(wish);

        //When
        Optional<Wishes> foundWish = wishesRepository.findByIdAndIsDeletedFalseAndIsConfirmed(wish.getId(), ConfirmStatus.CONFIRMED);

        //Then
        assertThat(foundWish).isPresent();
        assertThat(foundWish.get().getTitle()).isEqualTo("title");

        //When
        wishesService.deleteWish(wish.getId());
        foundWish = wishesRepository.findByIdAndIsDeletedFalseAndIsConfirmed(wish.getId(), ConfirmStatus.CONFIRMED);
        assertThat(foundWish).isNotPresent();
    }

}
