package com.example.marketapi.post.dto.res;

import com.example.marketapi.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostGetResDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;

    public PostGetResDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getMember().getName();
    }
}
