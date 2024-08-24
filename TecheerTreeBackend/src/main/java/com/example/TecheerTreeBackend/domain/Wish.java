package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;
    private String title;
    private String content;
    private String category;
    private Date created_at;
    private String is_confirm = "보류됨";
    private Boolean is_deleted = Boolean.FALSE;

    public Wish(String title, String content, String category, Date createAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.created_at = createAt;
    }

    public static Wish createWish(WishForm wishForm) {
        return new Wish(
                wishForm.getTitle(),
                wishForm.getContent(),
                wishForm.getCategory(),
                wishForm.getCreate_at()
        );
    }

    public void softDelete() {
        this.is_deleted = Boolean.TRUE;
    }

    public String confirm(WishConfirmForm wishConfirmForm) {
        return this.is_confirm = wishConfirmForm.getConfirm();
    }
}
