package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    }

    public BlogResponseDto getBlogById(Long id) {
        Blog blog = findBlog(id);

        return new BlogResponseDto(blog);
    }

@Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = findBlog(id);
        blog.update(requestDto);

        return new BlogResponseDto(blog);
    }

    public long deleteBlog(Long id, String password) {

        Blog blog = findBlog(id);

        blogRepository.delete(blog);

        return id;
    }

    public void likeBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException(("유효하지 않습니다.")));

        blog.setLikeCount(blog.getLikeCount() + 1);
        blogRepository.save(blog);
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

    }

}
