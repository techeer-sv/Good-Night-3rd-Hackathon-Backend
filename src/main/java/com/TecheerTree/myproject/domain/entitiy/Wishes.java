package com.TecheerTree.myproject.domain.entitiy;

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

    private Status is_confirm;

    public Wishes(){
    }

    public Wishes(String title, String content, Category category, LocalDate createdDate, Status is_confirm, boolean deleted_at) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = createdDate;
        this.is_confirm = is_confirm;
        this.deleted_at = deleted_at;
    }

    public void setCreatedDate(){
        this.createdDate = LocalDate.now();
    }
}

