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

    public PostGetResDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getName();
    }
}
