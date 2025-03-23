package com.example.marketapi.auth.jwt.repository;

import com.example.marketapi.redis.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByAccessToken(String accessToken);
}
