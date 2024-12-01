package com.example.marketapi.transact.controller;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.transact.request.ApproveRequest;
import com.example.marketapi.transact.request.ConfirmRequest;
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
    public ResponseEntity<Void> buyApprove(@RequestBody @Valid ApproveRequest approveRequest,
                                           @AuthenticationPrincipal MemberDetails memberDetails) {
        transactService.approve(approveRequest.productId(),
                approveRequest.buyerId(), memberDetails.getMemberId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmBuy(@RequestBody @Valid ConfirmRequest confirmRequest,
                                           @AuthenticationPrincipal MemberDetails memberDetails) {
        transactService.confirm(confirmRequest.productId(), memberDetails.getMemberId());

        return ResponseEntity.ok().build();
    }
}
