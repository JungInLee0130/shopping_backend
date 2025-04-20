package com.example.marketapi.post.dto.req;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateReqDto {
    String userId;
    String title;
    String content;

    Long memberId;

    public Post toEntity(Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
