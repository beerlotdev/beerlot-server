package com.beerlot.tool.fixture;

import com.beerlot.domain.auth.security.oauth.entity.ProviderType;
import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import com.beerlot.domain.review.Review;

import java.util.Set;

public class MemberFixture {
    public static Member createMember() {
        return Member.builder()
                .oauthId("123456789")
                .email("email@email.com")
                .imageUrl("https://beerlot.com/image_url")
                .username("비어러버")
                .statusMessage("That's my beer!")
                .roles(Set.of(RoleType.GUEST, RoleType.MEMBER))
                .provider(ProviderType.GOOGLE)
                .build();
    }

    public static Member createGuestMember() {
        return Member.builder()
                .oauthId("123456789")
                .email("email@email.com")
                .imageUrl("https://beerlot.com/image_url")
                .username("김비어")
                .roles(Set.of(RoleType.GUEST))
                .provider(ProviderType.GOOGLE)
                .build();
    }
}
