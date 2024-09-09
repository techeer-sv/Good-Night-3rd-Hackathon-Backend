package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  @Column(nullable = true)
  private Boolean isConfirmed;

  @OneToMany(mappedBy = "wish", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  public Wish(String title, String content, String category) {
    this.title = title;
    this.content = content;
    this.category = category;
    this.createdAt = LocalDateTime.now();
    this.isDeleted = false;
  }
  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public void setIsConfirmed(Boolean isConfirmed) {
    this.isConfirmed = isConfirmed;
  }
}