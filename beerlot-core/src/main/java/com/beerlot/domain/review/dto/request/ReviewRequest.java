package com.beerlot.domain.review.dto.request;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewRequest {

    @JsonProperty("content")
    private String content;

    @JsonProperty(value = "rate", required = true)
    private Float rate;

    @JsonProperty("image_url")
    private String imageUrl;

    @Builder
    public ReviewRequest(String content, Float rate, String imageUrl) {
        this.content = content;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }

    public static Review to(ReviewRequest reviewRequest, Beer beer, Member member) {
        return Review.builder()
                .content(reviewRequest.getContent())
                .rate(reviewRequest.getRate())
                .imageUrl(reviewRequest.getImageUrl())
                .beer(beer)
                .member(member)
                .build();
    }
}
