package com.beerlot.core.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @Builder
    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static AccessTokenResponse of(String accessToken) {

        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
