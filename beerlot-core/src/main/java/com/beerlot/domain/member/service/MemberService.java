package com.beerlot.domain.member.service;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import com.beerlot.domain.member.dto.request.MemberProfileRequest;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.domain.policy.PolicyType;
import com.beerlot.exception.ConflictException;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
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

    public Member findMemberByOauthId(String oauthId) {
        return memberRepository.findByOauthId(oauthId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER__NOT_EXIST.getMessage()));
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

    public void signUpMember(Member member, MemberRequest memberRequest) {
        if (member.getRoles().contains(RoleType.MEMBER)) {
            throw new ConflictException(ErrorMessage.MEMBER__ALREADY_SIGNED_UP.getMessage());
        }

        PolicyType.validateAgreeOnRequiredPolicies(memberRequest.getAgreedPolicies());

        member.updateProfile(MemberProfileRequest.builder()
                .statusMessage(memberRequest.getStatusMessage())
                .imageUrl(memberRequest.getImageUrl())
                .build());
        member.updateUsername(memberRequest.getUsername());
        member.updateAgreedPolicies(memberRequest.getAgreedPolicies());
        member.addRole(RoleType.MEMBER);
        member.setUsernameUpdatedAtToNow();
    }

    public MemberResponse getProfile(Member member) {
        return MemberResponse.of(member);
    }

    public MemberResponse updateProfile(Member member, MemberProfileRequest memberProfileRequest) {
        if (!member.getUsername().equals(memberProfileRequest.getUsername())) {
            if (!canUpdateUsername(member)) {
                throw new IllegalStateException(ErrorMessage.MEMBER__USERNAME_30DAYS.getMessage());
            }
            if (memberRepository.existsByUsername(memberProfileRequest.getUsername())) {
                throw new ConflictException(ErrorMessage.MEMBER__USERNAME_ALREADY_EXIST.getMessage());
            }
            member.updateUsername(memberProfileRequest.getUsername());
            member.setUsernameUpdatedAtToNow();
        }
        member.updateProfile(memberProfileRequest);
        return getProfile(member);
    }

    private boolean canUpdateUsername(Member member) {
        return member.getUsernameUpdatedAt().isBefore(OffsetDateTime.now().minus(Duration.ofDays(30)));
    }
}
