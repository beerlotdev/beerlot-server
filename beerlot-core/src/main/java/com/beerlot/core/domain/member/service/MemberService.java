package com.beerlot.core.domain.member.service;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.RoleType;
import com.beerlot.core.domain.member.repository.MemberRepository;
import com.beerlot.core.exception.ErrorMessage;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

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
                .roles(Set.of(RoleType.ROLE_GUEST))
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

    public void signUpMember(Member member, MemberCreateRequest memberCreateRequest) {
        if (member.getRoles().contains(RoleType.ROLE_MEMBER)) {
            throw new IllegalStateException(ErrorMessage.MEMBER__ALREADY_SIGNED_UP.getMessage());
        }

        member.updateUsername(memberCreateRequest.getUsername());
        member.updateStatusMessage(memberCreateRequest.getStatusMessage());
        member.updateImageUrl(memberCreateRequest.getImageUrl());
        member.addRole(RoleType.ROLE_MEMBER);
    }
}
