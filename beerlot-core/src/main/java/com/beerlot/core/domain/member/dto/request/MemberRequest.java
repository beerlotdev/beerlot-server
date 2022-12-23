package com.beerlot.core.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MemberRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("status_message")
    private String statusMessage;

    @JsonProperty("image_url")
    private String imageUrl;
}
