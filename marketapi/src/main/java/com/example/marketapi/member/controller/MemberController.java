package com.example.marketapi.member.controller;

import com.example.marketapi.auth.annotation.RoleUser;
import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.member.response.MemberResponse;
import com.example.marketapi.member.request.MemberEditRequest;
import com.example.marketapi.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RoleUser
    @GetMapping
    public ResponseEntity<MemberResponse> memberInfo(
            @AuthenticationPrincipal MemberDetails memberDetails) {
        MemberResponse memberResponse = memberService.memberInfo(memberDetails.getMemberId());
        return ResponseEntity.ok(memberResponse);
    }

    @RoleUser
    @PatchMapping
    public ResponseEntity<MemberResponse> memberEdit(@RequestBody @Valid MemberEditRequest memberEditRequest,
            @AuthenticationPrincipal MemberDetails memberDetails){
        MemberResponse memberResponse = memberService.memberEdit(memberEditRequest,
                        memberDetails.getMemberId());

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/loginGET")
    public String loginGET(String error, String logout){
        log.info("login get..........");
        log.info("logout:" + logout);
        return "/member/login";
    }
}
