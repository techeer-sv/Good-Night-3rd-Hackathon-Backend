package com.TecheerTree.myproject.domain.entitiy;

import com.TecheerTree.myproject.domain.dto.request.WishSaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Wishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long wishId;
    private String title;
    private String content;
    private LocalDate createdDate;
    private boolean deletedAt;

    private Category category;

    @Column(name = "is_confirm") // 데이터베이스 컬럼 이름과 매핑
    private Status status;

    public Wishes(){
    }

    public Wishes(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = LocalDate.now();  // 생성 시 현재 날짜로 설정
        this.status = Status.PENDING;
        this.deletedAt = false;
    }

    public static Wishes fromDTO(WishSaveRequest request){
        return new Wishes(
                request.getTitle(),
                request.getContent(),
                request.getCategory()
                );
    }
}

