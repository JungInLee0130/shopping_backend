package com.example.marketapi.member.controller;

import com.example.marketapi.auth.annotation.RoleUser;
import com.example.marketapi.member.dto.MemberDto;
import com.example.marketapi.member.dto.MemberEditRequest;
import com.example.marketapi.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<MemberDto> memberInfo(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(memberService.memberInfo(userDetails.getUsername()));
    }

    @RoleUser
    @PatchMapping
    public ResponseEntity<MemberDto> memberEdit(
            @RequestBody @Valid MemberEditRequest request,
            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(memberService.memberEdit(request, userDetails.getUsername()));
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
