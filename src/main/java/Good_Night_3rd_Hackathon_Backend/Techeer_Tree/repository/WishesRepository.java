package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishesRepository extends JpaRepository<Wishes, Long> {
}