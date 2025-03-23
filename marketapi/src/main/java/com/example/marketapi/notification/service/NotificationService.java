package com.example.marketapi.notification.service;

import com.example.marketapi.comment.request.CommentNotifyRequest;
import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.notification.repository.EmitterRepository;
import com.example.marketapi.notification.response.NotificationInfo;
import com.example.marketapi.notification.response.NotificationResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {
    private static final Long DEFAULT_TIMEOUT = 60L * 60 * 1000; // 1시간

    private final MemberRepository memberRepository;
    private final EmitterRepository emitterRepository;

    public SseEmitter subscribe(String lastEventId, String memberUUID, HttpServletResponse response) {
        String id = memberUUID + "_" + System.currentTimeMillis(); // 시간과 합쳐서 고유 id 생성

        SseEmitter sseEmitter = createEmitter(id);

        sendToClient(sseEmitter,id, "Connection",
                new NotificationResponse("Connection", "최초연결"));

        // lastEventId가 있으면 해당하는 id로 만든 emitter 다꺼내서 sse 다보냄.
        if (!lastEventId.isEmpty()) {
            Map<String, NotificationInfo> events = emitterRepository.findAllEventCacheStartWithId(id);
            events.entrySet()
                    .stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(sseEmitter, entry.getKey(), "alertComment", entry.getValue()));
        }

        return sseEmitter;
    }

    private SseEmitter createEmitter(String id){
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }

    private void sendToClient(SseEmitter sseEmitter, String id, String name, Object data) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .name(name)
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
            sseEmitter.completeWithError(exception);
        }

    }

    // 댓글단사람 모두에게 알림 전송해야함.
    public void notifyAllCommentReceiver(CommentNotifyRequest commentNotifyRequest) {
        Set<String> subscribers = new HashSet<>();
        subscribers.add("asdf-asdf");
        subscribers.add("asdf-asdf2");

        Member member = memberRepository.findByUuid(commentNotifyRequest.publisherUUID())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        String publisherUUID = member.getUuid();
        String publisherName = member.getName();

        NotificationInfo notificationInfo = NotificationResponse.createAlarmComment(publisherName);

        for (String subscriberUUID: subscribers) {
            if (subscriberUUID.equals(publisherUUID)) continue;

            Map<String, SseEmitter> emitters = emitterRepository
                    .findAllEmittersStartWithId(subscriberUUID);

            emitters.forEach(
                    (key,emitter) -> {
                        emitterRepository.saveEventCache(key,notificationInfo);

                        sendToClient(emitter, key, "Connection", notificationInfo);

                        // 데이터 저장하려면 하기
                    }
            );
        }
    }
}
