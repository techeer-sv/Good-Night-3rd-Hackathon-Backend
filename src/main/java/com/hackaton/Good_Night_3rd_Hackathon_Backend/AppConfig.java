package com.hackaton.Good_Night_3rd_Hackathon_Backend;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDao;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dao.CommentDaoImpl;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dao.WishDao;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dao.WishDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); // PostgreSQL JDBC 드라이버 클래스
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres"); // PostgreSQL 데이터베이스 URL
        dataSource.setUsername("postgres"); // 데이터베이스 사용자 이름
        dataSource.setPassword("1111"); // 데이터베이스 비밀번호
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public WishDao wishDao() {
        return new WishDaoImpl(jdbcTemplate());
    }

    @Bean
    public CommentDao commentDao() {return new CommentDaoImpl(jdbcTemplate());}


}