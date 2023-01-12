package com.beerlot.tool.fixture;

import com.beerlot.domain.auth.security.oauth.entity.ProviderType;
import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import com.beerlot.domain.review.Review;

import java.util.Collections;
import java.util.Set;

public class Fixture {

    /* ===== Member ===== */
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
    /* ===== Beer ===== */
    public static BeerInternational createBeerInternational() {
        return BeerInternational.builder()
                .name("호가든")
                .description("호가든 맥주입니다!")
                .originCountry("벨기에")
                .originCity("플람스브라반트")
                .language(LanguageType.KR)
                .beer(createBeer())
                .build();
    }

    public static Beer createBeer() {
        return Beer.builder()
                .id(1L)
                .imageUrl("https://beerlot.com/image_url")
                .volume(4.4f)
                .category(createCategory())
                .build();
    }

    /* ===== Review ===== */
    public static Review createReview() {
        return Review.builder()
                .content("이 맥주 최고!")
                .rate(5.0f)
                .imageUrl("https://beerlot.com/image_url")
                .member(createMember())
                .beer(createBeer())
                .build();
    }

    /* ===== Category ===== */
    public static CategoryInternational createCategoryInternational() {
        return CategoryInternational.builder()
                .name("에일")
                .description("에일 맥주는 풍부한 바디감과 목넘김이 부드럽다는 특징이 있습니다. 쌉싸름한 맛, 과일향 등 다양한 재료와의 조합이 돋보입니다.")
                .language(LanguageType.KR)
                .category(createCategory())
                .build();
    }

    public static Category createCategory() {
        return Category.builder()
                .id(1L)
                .parent(null)
                .build();
    }
}
