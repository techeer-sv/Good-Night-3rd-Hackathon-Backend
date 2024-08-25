package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto.RequestComment;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoImpl implements CommentDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CommentDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    };



    @Override
    public void createComment(Comment comment) {
        String sql = "INSERT INTO comments(content, wish_id) values(?,?)";
        jdbcTemplate.update(sql, comment.getContent(),comment.getWish_id());
    }

    @Override
    public void deleteComment(Long comment_id) {

    }

    @Override
    public void getAllComment(Long wish_id) {

    }
}
