package com.example.TecheerTreeBackend.domain;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wish_id")
    private Wish wish;

    private String content;
    private Date created_at;
    private Boolean is_deleted;

}
