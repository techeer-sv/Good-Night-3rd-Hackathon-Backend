package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto.RequestComment;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity.Comment;

import java.util.List;

public interface CommentDao {
//    댓글생성, 댓글삭제, 댓글조회(소원에 관한 모든 댓글)
    void createComment(Comment comment); //댓글이 달리는 주체 소원 id와 comment 내용
    void deleteComment(Long comment_id);

    List<Comment> getAllComment(Long wish_id);
}
