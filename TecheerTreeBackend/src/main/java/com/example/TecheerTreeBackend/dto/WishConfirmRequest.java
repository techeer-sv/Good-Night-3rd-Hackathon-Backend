package com.example.TecheerTreeBackend.dto;

import com.example.TecheerTreeBackend.domain.WishStatus;
import lombok.Getter;

@Getter
public class WishConfirmRequest {
    private WishStatus wishStatus;
}
