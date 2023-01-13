package com.beerlot.domain.review.dto.response;

import com.beerlot.domain.common.dto.BaseResponse;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class ReviewResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("rate")
    private Float rate;

    @JsonProperty("buy_from")
    private Set<String> buyFrom;

    @JsonProperty("like_count")
    private Long likeCount;

    @JsonProperty("beer_id")
    private Long beerId;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("member")
    private MemberResponse member;

    @Builder
    public ReviewResponse(Long id, String content, String imageUrl, Float rate, Set<String> buyFrom, Long likeCount, Long beerId,
                          Date updatedAt, MemberResponse member) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.rate = rate;
        this.buyFrom = buyFrom;
        this.likeCount = likeCount;
        this.beerId = beerId;
        this.member = member;
        this.updatedAt = updatedAt;
    }

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .rate(review.getRate())
                .buyFrom(review.getBuyFrom())
                .likeCount(review.getLikeCount())
                .beerId(review.getBeer().getId())
                .updatedAt(review.getUpdatedAt())
                .member(MemberResponse.of(review.getMember()))
                .build();
    }
}
