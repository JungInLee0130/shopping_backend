package com.example.marketapi.transact.controller;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.transact.request.ApproveRequest;
import com.example.marketapi.transact.service.TransactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacts")
public class TransactController {
    private final TransactService transactService;

    @PostMapping("/approve")
    public ResponseEntity<Void> buyApprove(@RequestBody @Valid ApproveRequest request,
                                           @AuthenticationPrincipal MemberDetails memberDetails) {
        //transactService.approve(approveRequest)
        return null;
    }
}
