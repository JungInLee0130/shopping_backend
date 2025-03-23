package com.example.marketapi.post.controller;


import com.example.marketapi.post.domain.Post;
import com.example.marketapi.post.dto.req.PostCreateReqDto;
import com.example.marketapi.post.dto.req.PostUpdateReqDto;
import com.example.marketapi.post.dto.res.PostGetResDto;
import com.example.marketapi.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping
    public PostGetResDto getPost(Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/all")
    public List<Post> getPostList() {
        return null;
        //postService.getPostList();
    }

    @PostMapping
    public void createPost(@RequestBody PostCreateReqDto postCreateReqDto) {
        postService.createPost(postCreateReqDto);
    }

    @PutMapping
    public void updatePost(@RequestBody PostUpdateReqDto postUpdateReqDto) {
        postService.updatePost(postUpdateReqDto);
    }

    @DeleteMapping
    public void deletePost(Long id) {
        postService.deletePost(id);
    }
}
