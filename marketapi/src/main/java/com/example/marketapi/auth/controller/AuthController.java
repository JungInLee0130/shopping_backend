package com.example.marketapi.auth.controller;

import com.example.marketapi.auth.jwt.service.TokenService;
import com.example.marketapi.auth.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @GetMapping("/success")
    public ResponseEntity<LoginResponse> loginSuccess(@Valid @RequestBody LoginResponse loginresponse){
        return ResponseEntity.ok(loginresponse);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {
        tokenService.deleteRefreshToken(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
