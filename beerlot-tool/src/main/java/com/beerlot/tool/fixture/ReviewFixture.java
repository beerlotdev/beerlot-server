package com.beerlot.tool.fixture;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.review.Review;

public class ReviewFixture {
    public static Review createReview(Beer beer) {
        return Review.builder()
                .content("이 맥주 최고!")
                .rate(5.0f)
                .imageUrl("https://beerlot.com/image_url")
                .buyFrom("편의점-GS편의점")
                .member(MemberFixture.createMember())
                .beer(beer)
                .build();
    }
}
