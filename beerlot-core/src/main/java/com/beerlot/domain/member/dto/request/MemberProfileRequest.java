package com.beerlot.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberProfileRequest {
    @NotNull
    @JsonProperty("status_message")
    private String statusMessage;

    @NotNull
    @JsonProperty("image_url")
    private String imageUrl;

    @Builder
    public MemberProfileRequest(String statusMessage, String imageUrl) {
        this.statusMessage = statusMessage;
        this.imageUrl = imageUrl;
    }
}
