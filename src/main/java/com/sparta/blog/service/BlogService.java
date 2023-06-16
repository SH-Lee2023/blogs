package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BlogService {

    private final JdbcTemplate jdbcTemplate; // jdbc 사용할꺼면 변수 선언

    public BlogService(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        //RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        // DB 저장
        BlogRepository blogRepository = new BlogRepository(jdbcTemplate);
        Blog savsBlog = blogRepository.save(blog);
        return new BlogResponseDto(savsBlog);



//        // Entity -> ResponseDto
//        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);
//
//        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        // DB 조회
        BlogRepository blogRepository = new BlogRepository(jdbcTemplate);
        return blogRepository.findAll();

    }
    public BlogResponseDto getBlogById(Long id) {
        BlogRepository blogRepository = new BlogRepository(jdbcTemplate);
        return blogRepository.findById(id);
    }


    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        BlogRepository blogRepository = new BlogRepository(jdbcTemplate);
        String password = blogRepository.getPasswordById(id);
        if (password.equals(requestDto.getPassword())) {
            blogRepository.update(id, requestDto);
            return blogRepository.findById(id);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public boolean deleteBlog(Long id, String password) {
        BlogRepository blogRepository = new BlogRepository(jdbcTemplate);
        String storedPassword = blogRepository.getPasswordById(id);
        if (storedPassword.equals(password)) {
            blogRepository.delete(id);
            return true;  // 삭제 성공
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}
