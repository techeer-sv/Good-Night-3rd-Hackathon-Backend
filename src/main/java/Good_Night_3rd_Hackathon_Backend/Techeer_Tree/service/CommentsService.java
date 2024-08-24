package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Comments;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.CommentsDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.WishesDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository.CommentsRepository;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository.WishesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final WishesRepository wishesRepository;

    public CommentsService(CommentsRepository commentsRepository, WishesRepository wishesRepository) {
        this.commentsRepository = commentsRepository;
        this.wishesRepository = wishesRepository;
    }

    @Transactional
    public Comments createComment(Long wishId, String content) {
        Wishes wish = wishesRepository.findById(wishId).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));

        if (wish.getIsConfirm() != Wishes.WishesStatus.APPROVED) {
            throw new IllegalArgumentException("승인되지 않은 소원입니다.");
        }

        Comments comment = new Comments();
        comment.setWish(wish);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        return commentsRepository.save(comment);
    }

    @Transactional
    public Page<Comments> getCommentList(Long wishId, Pageable pageable) {
        Wishes wish = wishesRepository.findById(wishId).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));
        return commentsRepository.getCommentList(wish, pageable);
    }

    @Transactional
    public void deleteComment(Long wishId, Long commentId) {
        Wishes wish = wishesRepository.findById(wishId).orElseThrow(() -> new IllegalArgumentException("해당 소원이 존재하지 않습니다."));

        Comments comment = commentsRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getWish().getId().equals(wishId)) {
            throw new IllegalArgumentException("해당 소원의 댓글이 아닙니다.");
        }

        comment.setIsDeleted(true);
        commentsRepository.save(comment);
    }
}
