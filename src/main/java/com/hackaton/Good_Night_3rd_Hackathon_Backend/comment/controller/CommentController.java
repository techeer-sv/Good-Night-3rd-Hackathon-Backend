package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.controller;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDao;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDaoImpl;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto.RequestComment;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity.Comment;
import org.springframework.web.bind.annotation.*;

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
}
