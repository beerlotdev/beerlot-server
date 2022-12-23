package com.beerlot.core.domain.review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReviewRequest {

    @JsonProperty("content")
    private String content;

    @JsonProperty(value = "rate", required = true)
    private Float rate;

    @JsonProperty("image_url")
    private String imageUrl;

}
