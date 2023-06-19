package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;

import java.util.List;

public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {

        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        //RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        // DB 저장

        Blog savsBlog = blogRepository.save(blog);
        return new BlogResponseDto(savsBlog);



//        // Entity -> ResponseDto
//        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);
//
//        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        // DB 조회

        return blogRepository.findAll();

    }
    public BlogResponseDto getBlogById(Long id) {

        return blogRepository.findById(id);
    }


    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {

        String password = blogRepository.getPasswordById(id);
        if (password.equals(requestDto.getPassword())) {
            blogRepository.update(id, requestDto);
            return blogRepository.findById(id);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public boolean deleteBlog(Long id, String password) {

        String storedPassword = blogRepository.getPasswordById(id);
        if (storedPassword.equals(password)) {
            blogRepository.delete(id);
            return true;  // 삭제 성공
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}
