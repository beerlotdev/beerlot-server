package com.beerlot.domain.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class MemberCreateRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("status_message")
    private String statusMessage;

    @Builder
    public MemberCreateRequest(String username, String imageUrl, String statusMessage) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.statusMessage = statusMessage;
    }
}
