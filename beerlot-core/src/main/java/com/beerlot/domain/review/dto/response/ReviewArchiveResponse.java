package com.beerlot.domain.review.dto.response;

import com.beerlot.domain.beer.dto.response.BeerSimpleResponse;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.Date;

@NoArgsConstructor
public class ReviewArchiveResponse {

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

    @Embedded
    @JsonProperty("beer")
    private BeerResponse beer;


    public static class BeerResponse {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("image_url")
        private String imageUrl;
    }
}