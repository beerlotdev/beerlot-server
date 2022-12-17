package com.beerlot.core.domain.auth.dto.helper;

import com.beerlot.api.generated.model.AccessTokenResponse;

public class AccessTokenResponseHelper {
    public static AccessTokenResponse of(String accessToken) {

        AccessTokenResponse accessTokenResponse = new AccessTokenResponse();

        accessTokenResponse.setAccessToken(accessToken);

        return accessTokenResponse;
    }
}
