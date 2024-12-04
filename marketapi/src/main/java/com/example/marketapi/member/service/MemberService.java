package com.example.marketapi.member.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.response.MemberResponse;
import com.example.marketapi.member.request.MemberEditRequest;
import com.example.marketapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse memberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        return new MemberResponse(member);
    }

    @Transactional
    public MemberResponse memberEdit(MemberEditRequest memberEditRequest, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        member.updateMember(memberEditRequest);

        return new MemberResponse(member);
    }
}
