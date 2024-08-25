package com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Wish {
    private Long wishId;
    private String title;
    private String content;
    private String category;
    private java.sql.Timestamp registrationDate;
    private String isConfirmed;

}
