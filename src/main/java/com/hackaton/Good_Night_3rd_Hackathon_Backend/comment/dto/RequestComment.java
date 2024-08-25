package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class RequestComment {
    private String content;
    private  Long wish_id;
}
