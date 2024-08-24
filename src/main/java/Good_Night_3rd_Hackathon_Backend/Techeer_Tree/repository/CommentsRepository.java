package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Comments;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT c FROM Comments c WHERE c.wish = :wish AND c.isDeleted = false")
    Page<Comments> getCommentList(Wishes wish, Pageable pageable);
}
