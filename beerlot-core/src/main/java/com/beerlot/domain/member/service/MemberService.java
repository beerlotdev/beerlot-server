package com.beerlot.domain.member.service;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.exception.ConflictException;
import com.beerlot.exception.ErrorMessage;
import com.beerlot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findMemberByOauthId(String oauthId) {
        return memberRepository.findByOauthId(oauthId);
    }

    public Member createMember(OAuthUserPrincipal oAuthUser) {
        Member member = Member.builder()
                .oauthId(oAuthUser.getId())
                .email(oAuthUser.getEmail())
                .username(oAuthUser.getUsername())
                .imageUrl(oAuthUser.getImageUrl())
                .provider(oAuthUser.getProvider())
                .roles(Set.of(RoleType.GUEST))
                .build();
        return memberRepository.save(member);
    }

    public long countByUsername(String username) {
        return memberRepository.countByUsername(username);
    }

    public void updateEmail(Member member, String email) {
        member.updateEmail(email);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER__NOT_EXIST));
    }

    public void signUpMember(Member member, MemberRequest memberRequest) {
        if (member.getRoles().contains(RoleType.MEMBER)) {
            throw new ConflictException(ErrorMessage.MEMBER__ALREADY_SIGNED_UP.getMessage());
        }

        member.updateUsername(memberRequest.getUsername());
        member.updateStatusMessage(memberRequest.getStatusMessage());
        member.updateImageUrl(memberRequest.getImageUrl());
        member.addRole(RoleType.MEMBER);
    }
}
