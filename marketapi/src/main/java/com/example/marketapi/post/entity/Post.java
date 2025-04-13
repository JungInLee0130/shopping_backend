package com.example.marketapi.post.entity;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "POSTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_title")
    private String title; // 제목
    @Column(name = "post_content")
    private String content; // 작성내용

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 작성날짜, 수정날짜 : BaseTimeEntity

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
