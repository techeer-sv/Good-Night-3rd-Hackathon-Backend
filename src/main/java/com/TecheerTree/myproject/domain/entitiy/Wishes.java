package com.TecheerTree.myproject.domain.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
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
    private Category category;

    private LocalDate createdDate;
    private Confirm is_confirm;
    private boolean deleted_at;

    public Wishes(){
    }

    public Wishes(String title, String content, Category category, LocalDate createdDate, Confirm is_confirm, boolean deleted_at) {
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

