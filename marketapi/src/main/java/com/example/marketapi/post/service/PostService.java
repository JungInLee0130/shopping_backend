package com.example.marketapi.post.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.post.domain.Post;
import com.example.marketapi.post.dto.req.PostCreateReqDto;
import com.example.marketapi.post.dto.req.PostUpdateReqDto;
import com.example.marketapi.post.dto.res.PostGetResDto;
import com.example.marketapi.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    public PostGetResDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "해당 게시글이 없습니다."));
        return new PostGetResDto(post);
    }

    public void getPostList() {

    }

    @Transactional
    public void createPost(PostCreateReqDto reqDto) {
        postRepository.save(reqDto.toEntity());
    }

    @Transactional
    public void updatePost(PostUpdateReqDto reqDto) {
    }

    @Transactional
    public void deletePost(Long id) {
    }
}
