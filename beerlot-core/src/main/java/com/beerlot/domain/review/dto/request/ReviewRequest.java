package com.beerlot.domain.review.dto.request;

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
}
