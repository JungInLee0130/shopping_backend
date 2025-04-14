package com.example.marketapi.comment.entity;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @Column(name = "comment_content")
    private String content;

    @Builder
    public Comment(Long id, Member member, String content) {
        this.id = id;
        this.member = member;
        this.content = content;
    }
}
