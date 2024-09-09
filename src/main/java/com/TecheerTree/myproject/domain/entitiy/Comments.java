package com.TecheerTree.myproject.domain.entitiy;

import com.TecheerTree.myproject.domain.dto.request.CommentSaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDate createdDate;
    private Long wishId;  // 소원의 ID만 저장
    private boolean deletedAt;

    public Comments() {
    }

    public Comments(String content, Long wishId) {
        this.content = content;
        this.createdDate = LocalDate.now();  // 생성 시 현재 날짜로 설정
        this.wishId = wishId;
        this.deletedAt = false;  // 기본값으로 false 설정
    }

    public Comments(String content, LocalDate createdDate, Long wishId, boolean deletedAt) {
        this.content = content;
        this.createdDate = createdDate;
        this.wishId = wishId;
        this.deletedAt = deletedAt;
    }

    public static Comments fromDTO(CommentSaveRequest commentSaveRequest){
       return new Comments(commentSaveRequest.getContent(), commentSaveRequest.getWishId());
    }

}
