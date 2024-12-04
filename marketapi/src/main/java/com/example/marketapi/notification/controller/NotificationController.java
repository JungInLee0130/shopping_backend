package com.example.marketapi.notification.controller;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.notification.service.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping()
    public ResponseEntity<SseEmitter> subscribe(@AuthenticationPrincipal MemberDetails memberDetails,
                                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
                                                HttpServletResponse response) {
        response.setHeader("X-Accel-Buffering", "no");
        notificationService.subscribe(lastEventId, memberDetails.getMemberUUID(), response);

        return ResponseEntity.ok().build();
    }
}
