package com.sparta.blog.dto;

import com.sparta.blog.entity.Blog;
import lombok.Getter;

import java.util.PrimitiveIterator;

@Getter
public class BlogResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String password;
    private String createdAt;

    public BlogResponseDto(Blog blog) {

        this.id = blog.getId();
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.password = blog.getPassword();
    }

    public BlogResponseDto(Long id, String title, String username, String contents, String createdAt) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
