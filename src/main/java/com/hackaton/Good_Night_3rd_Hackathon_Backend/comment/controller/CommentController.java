package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.controller;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDao;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDaoImpl;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto.RequestComment;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/comment")
@RestController
public class CommentController {
    CommentDao commentDao;
    public CommentController(CommentDao commentDao){
        this.commentDao=commentDao;
    }
    @PostMapping("/create")
    public void createComment(@RequestBody Comment comment){
        commentDao.createComment(comment);
    }

    @PatchMapping("/softdelete/{comment_id}")
    public void softDeleteComment(@PathVariable("comment_id") Long comment_id){
        commentDao.deleteComment(comment_id);
    }

    @GetMapping("/{wish_id}")
    public List<Comment> getComment(@PathVariable("wish_id") Long wish_id){
        return commentDao.getAllComment(wish_id);
    }
}
