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
    private boolean deleted_at;

    private Category category;

    @Column(name = "is_confirm") // 데이터베이스 컬럼 이름과 매핑
    private Status status;

    public Wishes(){
    }

    public Wishes(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Wishes(String title, String content, Category category, LocalDate createdDate, Status status, boolean deleted_at) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = createdDate;
        this.status = status;
        this.deleted_at = deleted_at;
    }

    public static Wishes fromDTO(WishSaveRequest request){
        return new Wishes(
                request.getTitle(),
                request.getContent(),
                request.getCategory()
                );
    }
}

