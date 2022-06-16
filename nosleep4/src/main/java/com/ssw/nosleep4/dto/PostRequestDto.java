package com.ssw.nosleep4.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDto {

    private String nickname;


    private String title;


    private String imageurl;


    private String content;
}
