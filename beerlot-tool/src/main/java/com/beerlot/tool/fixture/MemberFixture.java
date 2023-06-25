package com.beerlot.tool.fixture;

import com.beerlot.domain.auth.security.oauth.entity.ProviderType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;

import java.time.OffsetDateTime;
import java.util.Set;

public class MemberFixture {

    public static Member createMember() {
        return Member.builder()
                .username("Beer Lover")
                .roles(Set.of(RoleType.MEMBER))
                .email("official_member@email.com")
                .oauthId("11111111")
                .provider(ProviderType.GOOGLE)
                .statusMessage("Perfect day for one cold beer!")
                .imageUrl("https://beerlot.com/profiles/official_member")
                .usernameUpdatedAt(OffsetDateTime.parse("2022-12-31T10:30:40+00:00"))
                .build();
    }

    public static Member createGuestMember() {
        return Member.builder()
                .username("Pina Colada")
                .roles(Set.of(RoleType.GUEST))
                .email("guest_member@email.com")
                .oauthId("22222222")
                .provider(ProviderType.NAVER)
                .build();
    }
}
