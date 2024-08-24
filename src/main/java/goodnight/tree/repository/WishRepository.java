package goodnight.tree.repository;

import goodnight.tree.domain.dao.Comment;
import goodnight.tree.domain.dao.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    // Custom query methods can be added here
    Page<Wish> findAllByIsConfirm(Wish.WishStatus status, Pageable pageable);

}
