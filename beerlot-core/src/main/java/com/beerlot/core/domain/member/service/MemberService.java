package com.beerlot.core.domain.member.service;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.ProviderType;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.RoleType;
import com.beerlot.core.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member createMember(MemberCreateRequest memberCreateRequest) {
        Member member = Member.builder()
                .email(memberCreateRequest.getEmail())
                .username(memberCreateRequest.getUsername())
                .imageUrl(memberCreateRequest.getImageUrl())
                .providerType(ProviderType.valueOf(memberCreateRequest.getProviderType()))
                .roleType(RoleType.GENERAL)
                .statusMessage(memberCreateRequest.getStatusMessage())
                .build();
        return memberRepository.save(member);
    }
}
