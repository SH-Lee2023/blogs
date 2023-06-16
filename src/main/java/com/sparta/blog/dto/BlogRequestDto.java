package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {

    private String title;
    private String username;
    private String contents;
    private String password;

    public BlogRequestDto(String title, String username, String contents, String password) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
}

