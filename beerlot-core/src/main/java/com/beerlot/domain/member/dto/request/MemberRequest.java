package com.beerlot.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("status_message")
    private String statusMessage;

    @JsonProperty("image_url")
    private String imageUrl;

    @Builder
    public MemberRequest(String username, String statusMessage, String imageUrl) {
        this.username = username;
        this.statusMessage = statusMessage;
        this.imageUrl = imageUrl;
    }
}
