package com.beerlot.domain.review.dto.response;

import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @JsonProperty("like_count")
    private Long likeCount;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("member")
    private MemberResponse member;

    @Builder
    public ReviewResponse(Long id, String content, String imageUrl, Float rate, Long likeCount,
                          Date updatedAt, MemberResponse member) {
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
}
