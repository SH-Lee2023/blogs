package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blogs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {

        return blogService.createBlog(requestDto);

    }

    @GetMapping("/blogs")
    public List<BlogResponseDto> getBlogs() {

        return blogService.getBlogs();
    }
    // 수정중
    @GetMapping("/blogs/{id}")
    public BlogResponseDto getBlogById(@PathVariable Long id) {

        return blogService.getBlogById(id);
    }
    // 여기까지 추가

    @PutMapping("/memos/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {


        return blogService.updateBlog(id, requestDto);
    }
//    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
//        BlogService blogService = new BlogService(jdbcTemplate);
//        return blogService.updateBlog(id, requestDto);


    @DeleteMapping("/blogs/{id}")
    public boolean deleteBlog(@PathVariable Long id, @RequestParam String password) {



        return blogService.deleteBlog(id, password);
    }

}
