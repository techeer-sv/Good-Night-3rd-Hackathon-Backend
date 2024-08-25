package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto.RequestComment;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
        String sql = "UPDATE comments SET is_deleted = true, deleted_at=NOW() WHERE id = ?";
        jdbcTemplate.update(sql, comment_id);
    }

    @Override
    public List<Comment> getAllComment(Long wish_id) {
        // as 뒤에 별칭으로 entity객체 안에 있는 변수와 이름을 동일하게 안해주면 오류가 나옴.
        String sql = "SELECT B.id as comment_id ,B.content,B.registrationDate,B.wish_id, B.is_deleted, B.deleted_at FROM wishes A, comments B WHERE A.id IN (B.wish_id) AND A.id = ?";
        return jdbcTemplate.query(sql, new CommentRowMapper(),wish_id);

    }

    private static class CommentRowMapper implements RowMapper<Comment>{

        @Override // RowMapper의 인터페이스 오버라이딩, rs는 쿼리의 결과,
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setComment_id(rs.getLong("comment_id"));
            comment.setContent(rs.getString("content"));
            comment.setWish_id(rs.getLong("wish_id"));
            comment.set_deleted(rs.getBoolean("is_deleted"));
            Timestamp registrationDate = rs.getTimestamp("registrationDate");
            if (registrationDate != null) {
                comment.setRegistrationDate(Timestamp.valueOf(registrationDate.toLocalDateTime()));
            }
            Timestamp deleted_at = rs.getTimestamp("deleted_at"); // null값 허용
            if(deleted_at !=null){
                comment.setDeleted_at(Timestamp.valueOf(deleted_at.toLocalDateTime()));
            }




            return comment;
        }
    }
}
