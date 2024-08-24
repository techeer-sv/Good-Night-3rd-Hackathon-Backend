package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.controller;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Comments;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.CommentsDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service.CommentsService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/wishes/{wishId}/comments")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable Long wishId, @RequestBody CommentsDto commentsDto) {
        try {
            Comments createdComment = commentsService.createComment(wishId, commentsDto.getContent());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.equals("해당 소원이 존재하지 않습니다.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            } else if (errorMessage.equals("삭제된 소원입니다.") || errorMessage.equals("승인되지 않은 소원입니다.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
    }

    @GetMapping
    public ResponseEntity<?> getComments(
            @PathVariable Long wishId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<Comments> comments = commentsService.getCommentList(wishId, pageable);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.equals("해당 소원이 존재하지 않습니다.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            } else if (errorMessage.equals("삭제된 소원입니다.") || errorMessage.equals("승인되지 않은 소원입니다.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteWish(@PathVariable Long wishId, @PathVariable Long commentId) {
        try {
            commentsService.deleteComment(wishId, commentId);
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.equals("해당 소원이 존재하지 않습니다.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            } else if (errorMessage.equals("삭제된 소원입니다.") || errorMessage.equals("승인되지 않은 소원입니다.") || errorMessage.equals("해당 댓글이 존재하지 않습니다.") || errorMessage.equals("해당 소원의 댓글이 아닙니다.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
    }
}
