package com.example.marketapi.post.domain;

import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column
    private String title; // 제목
    @Column
    private String content; // 작성내용
    @Column
    private String nickname; // 작성자

    // 작성날짜, 수정날짜


    @Builder
    public Post(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
