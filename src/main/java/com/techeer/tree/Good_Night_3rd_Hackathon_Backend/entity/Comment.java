package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wish_id", nullable = false)
  private Wish wish;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private LocalDateTime created_at = LocalDateTime.now();

  @Column(nullable = false)
  private Boolean is_deleted = false;

  public Comment(String content, Wish wish) {
    this.content = content;
    this.wish = wish;
  }

  public void setIsDeleted(Boolean isDeleted) {
    this.is_deleted = is_deleted;
  }
}