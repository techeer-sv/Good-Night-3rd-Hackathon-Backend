package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO보다 IDENTITY가 더 적합한 경우가 많음
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(nullable = true)
  private Boolean isConfirmed;

  @Column(nullable = false)
  private Boolean isDeleted = false;

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