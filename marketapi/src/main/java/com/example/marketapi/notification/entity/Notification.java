package com.example.marketapi.notification.entity;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "NOTIFICATIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "notification_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @Builder
    public Notification(Long id, String content, Member member) {
        this.id = id;
        this.content = content;
        this.member = member;
    }
}
