package com.example.marketapi.post.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateReqDto {
    Long id;
    String content;
}
