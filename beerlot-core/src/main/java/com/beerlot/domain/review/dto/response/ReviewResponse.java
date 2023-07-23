package com.beerlot.domain.review.dto.response;

import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

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

    @JsonProperty("like_count")
    private Long likeCount;

    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;

    @JsonProperty("member")
    private MemberResponse member;

    @JsonProperty("beer")
    private BeerResponse beer;


    @Builder
    public ReviewResponse(Long id, String content, String imageUrl, Float rate, Long likeCount,
                          OffsetDateTime updatedAt, MemberResponse member) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.rate = rate;
        this.likeCount = likeCount;
        this.member = member;
        this.updatedAt = updatedAt;
    }

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .rate(review.getRate())
                .likeCount(review.getLikeCount())
                .updatedAt(review.getUpdatedAt())
                .member(MemberResponse.of(review.getMember()))
                .build();
    }

    public static class BeerResponse {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;
    }
}
