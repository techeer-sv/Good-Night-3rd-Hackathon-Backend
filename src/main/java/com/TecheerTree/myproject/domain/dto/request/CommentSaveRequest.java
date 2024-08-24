package com.TecheerTree.myproject.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentSaveRequest {

    private String content;
    private Long wishId;  // 소원의 ID만 저장

    public CommentSaveRequest() {
    }



}

