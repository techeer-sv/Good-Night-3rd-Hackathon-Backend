package goodnight.tree.service;

import goodnight.tree.domain.dao.Comment;
import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.CommentSaveRequest;
import goodnight.tree.domain.dto.response.CommentResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.repository.CommentRepository;
import goodnight.tree.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.transaction.Transactional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service @Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final WishRepository wishRepository;

    // 댓글 저장
    public void saveComment(CommentSaveRequest request) {
        Wish wish = wishRepository.findById(request.getWishId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 게시글입니다.")
        );
        Comment comment = request.toEntity(wish);
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}