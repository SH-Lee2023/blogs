package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {

        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {

        Blog blog = new Blog(requestDto);

        Blog savsBlog = blogRepository.save(blog);

        return new BlogResponseDto(savsBlog);

    }

    public List<BlogResponseDto> getBlogs() {

        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
//        List<Blog> blogs = blogRepository.findAll();
//        List<BlogResponseDto> responseDtos = new ArrayList<>();
//
//        for (Blog blog : blogs) {
//            responseDtos.add(new BlogResponseDto(blog));
//        }
//
//        return responseDtos;

    }

    public BlogResponseDto getBlogById(Long id) {
        Blog blog = findBlog(id);

        return new BlogResponseDto(blog);
    }

@Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = findBlog(id);

//        if (blog == null) {
//            // 존재하지 않는 블로그에 대한 예외 처리
//            // 예외 처리 방법은 상황에 따라 적절하게 변경하세요.
//            throw new IllegalArgumentException("Invalid blog ID: " + id);
//        }
//        blog.update(requestDto);
        blog.update(requestDto);

        return new BlogResponseDto(blog);
    }

    public long deleteBlog(Long id, String password) {

        Blog blog = findBlog(id);

        blogRepository.delete(blog);

        return id;
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

    }

}
