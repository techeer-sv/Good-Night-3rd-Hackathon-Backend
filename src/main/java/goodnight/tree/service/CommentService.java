package goodnight.tree.service;

import goodnight.tree.domain.dao.Comment;
import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.CommentSaveRequest;
import goodnight.tree.domain.dto.response.CommentResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.repository.CommentRepository;
import goodnight.tree.repository.WishRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service @Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final WishRepository wishRepository;

    // 댓글 저장
    public void saveComment(CommentSaveRequest request) {
        Wish wish = wishRepository.findByIdAndDeletedAtIsNull(request.getWishId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다.")
        );
        Comment comment = request.toEntity(wish);
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByIdAndDeletedAtIsNull(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.softDelete();
    }

    // 댓글 조회(페이지네이션)
    @Transactional(readOnly=true)
    public List<CommentResponse> findCommentList(Pageable pageable, Long wishId) {
        Page<Comment> CommentList = commentRepository.findAllByWishIdAndDeletedAtIsNull(wishId,pageable);
        return CommentList.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}