package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.service.BlogService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final JdbcTemplate jdbcTemplate; // jdbc 사용할꺼면 변수 선언

    public BlogController(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/blogs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.createBlog(requestDto);

    }

    @GetMapping("/blogs")
    public List<BlogResponseDto> getBlogs() {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.getBlogs();
    }
    // 수정중
    @GetMapping("/blogs/{id}")
    public BlogResponseDto getBlogById(@PathVariable Long id) {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.getBlogById(id);
    }
    // 여기까지 추가

    @PutMapping("/memos/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        BlogService blogService;
        blogService = new BlogService(jdbcTemplate);
        return blogService.updateBlog(id, requestDto);
    }
//    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
//        BlogService blogService = new BlogService(jdbcTemplate);
//        return blogService.updateBlog(id, requestDto);


    @DeleteMapping("/blogs/{id}")
    public boolean deleteBlog(@PathVariable Long id, @RequestParam String password) {

        BlogService blogService;
        blogService = new BlogService(jdbcTemplate);
        return blogService.deleteBlog(id, password);
    }

    private Blog findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM blog WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Blog blog = new Blog();
                blog.setUsername(resultSet.getString("username"));
                blog.setContents(resultSet.getString("contents"));
                return blog;
            } else {
                return null;
            }
        }, id);
    }


}
