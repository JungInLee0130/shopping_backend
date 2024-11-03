package com.example.marketapi.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisMessageService {
    private final RedisTemplate<String, Object> redisTemplate;
}
