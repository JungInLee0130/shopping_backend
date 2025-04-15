package com.example.marketapi.post.dto.req;

import com.example.marketapi.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateReqDto {
    String userId;
    String title;
    String nickname;
    String content;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .nickname(nickname)
                .build();
    }
}
