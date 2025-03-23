package com.example.marketapi.notification.repository;

import com.example.marketapi.notification.response.NotificationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
    // 인메모리 방식...
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, NotificationInfo> eventCache = new ConcurrentHashMap<>();

    public SseEmitter save(String id, SseEmitter sseEmitter) {
        emitters.put(id, sseEmitter);
        return sseEmitter;
    }

    // ?
    public void saveEventCache(String id, NotificationInfo notificationInfo) {
        eventCache.put(id, notificationInfo);
    }

    public SseEmitter getEmitter(String id) {
        return emitters.get(id);
    }

    // 시각 상관없이 모두 동일한 id가 만든 emitter return
    public Map<String, SseEmitter> findAllEmittersStartWithId(String id) {
        return emitters.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, NotificationInfo> findAllEventCacheStartWithId(String id) {
        return eventCache.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void deleteById(String id){
        emitters.remove(id);
    }

    public void deleteAllStartWithById(String id) {
        emitters.forEach((key, emitter) ->{
            if (key.startsWith(id)) emitters.remove(key);
        });
    }

    public void deleteAllEventCacheStartWithById(String id) {
        eventCache.forEach((key, notificationInfo) ->{
            if (key.startsWith(id)) eventCache.remove(key);
        });
    }
}
