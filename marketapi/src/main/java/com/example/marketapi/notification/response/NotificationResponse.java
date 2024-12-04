package com.example.marketapi.notification.response;

import com.example.marketapi.comment.request.CommentAddRequest;

public record NotificationResponse(String type,
                                   String content) implements NotificationInfo{

    public static NotificationResponse createAlarmComment(String publisherName){
        return new NotificationResponse("Connection", publisherName + " 님이 댓글을 남겼습니다.");
    }

    public static NotificationResponse create(CommentAddRequest commentAddRequest) {
        return new NotificationResponse("Connection", commentAddRequest.nickname() + "님이 댓글을 남겼습니다.");
    }
}
