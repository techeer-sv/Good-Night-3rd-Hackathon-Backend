package goodnight.tree.repository;

import goodnight.tree.domain.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Custom query methods can be added here
    Page<Comment> findAllByWishIdAndDeletedAtIsNull(Long wishId, Pageable pageable);

    Optional<Comment> findByIdAndDeletedAtIsNull(Long commentId);
}
