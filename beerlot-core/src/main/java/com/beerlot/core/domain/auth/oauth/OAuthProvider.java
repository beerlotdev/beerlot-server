package com.beerlot.core.domain.auth.oauth;

public interface OAuthProvider {
    String buildLoginRequestUrl();
    GoogleOAuthTokenResponse requestToken(String code);

    OAuthUser requestUserInformation(String accessToken);
}
