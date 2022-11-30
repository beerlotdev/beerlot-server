package com.beerlot.core.domain.auth.oauth;

import com.beerlot.core.domain.auth.ProviderType;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.RoleType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class OAuthUser {
    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract ProviderType getProviderType();

    public Member toMember() {
        Member member = Member.builder()
                .email(getEmail())
                .username(getName())
                .providerType(getProviderType())
                .roleType(RoleType.GENERAL)
                .imageUrl(getImageUrl())
                .build();
        return member;
    }
}
