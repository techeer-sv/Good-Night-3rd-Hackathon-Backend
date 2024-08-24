package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.controller;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Comments;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.CommentsDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service.CommentsService;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
