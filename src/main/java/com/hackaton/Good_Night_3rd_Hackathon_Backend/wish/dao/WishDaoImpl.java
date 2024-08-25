package com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dao;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class WishDaoImpl implements WishDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishDaoImpl(JdbcTemplate jdbcTemplate) { //의존성 주입.
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createWish(Wish wish) {
        String sql = "INSERT INTO wishes(title,content,category,registrationdate) VALUES (?,?,?,?)";
        Timestamp current = new Timestamp(new Date().getTime());
        jdbcTemplate.update(sql, wish.getTitle(),wish.getContent(),wish.getCategory(),current);
        return;
    }
    //메서드 시그니처를 보면 인자들이 어떻게 전달되는지 이해할 수 있음.
    @Override //@PathVariable은 URL 경로에 포함된 값을 메서드 매개변수로 바인딩할 때 사용
              // @RequestParam은 URL 쿼리 파라미터를 메서드 매개변수로 바인딩할 때 사용합니다
    public Wish getWish(Long id) {
        String sql = "SELECT * FROM wishes WHERE id = ?";
        System.out.println(id);
        return jdbcTemplate.queryForObject(sql, new WishRowMapper(), id);
    }

    @Override
    public List<Wish> getWishList() {
        String sql = "SELECT * FROM wishes";
        return jdbcTemplate.query(sql, new WishRowMapper());
    }

    @Override
    public void deleteWish(Long id) {
        String sql = "DELETE FROM wishes WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void confirmWish(Long id, Boolean confirm) {
        String sql = "UPDATE wishes SET isConfirmed = ? WHERE id = ?";
        jdbcTemplate.update(sql,confirm,id);

    }

    // 내부 RowMapper 클래스
    private static class WishRowMapper implements RowMapper<Wish> {
        @Override // rs는 쿼리의 결과,
        public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
            Wish wish = new Wish();
            wish.setWishId(rs.getLong("id"));
            wish.setTitle(rs.getString("title"));
            wish.setContent(rs.getString("content"));
            wish.setCategory(rs.getString("category"));
//            wish.setRegistrationDate(Timestamp.valueOf(rs.getTimestamp("registrationDate").toLocalDateTime()));
            Timestamp registrationDate = rs.getTimestamp("registrationDate");
            if (registrationDate != null) {
                wish.setRegistrationDate(Timestamp.valueOf(registrationDate.toLocalDateTime()));
            }
            wish.setIsConfirmed(rs.getString("isConfirmed"));
            return wish;
        }
    }
}