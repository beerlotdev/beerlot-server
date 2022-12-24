package com.beerlot.domain.auth.security.oauth.repository;

import com.beerlot.domain.auth.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthorizationRequestCookieRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final int COOKIE_EXPIRY_IN_SECONDS = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, CookieUtils.COOKIE_NAME_AUTH_REQUEST)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_AUTH_REQUEST);
            CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_REDIRECT_URL);
            return;
        }
        CookieUtils.addCookie(response, CookieUtils.COOKIE_NAME_AUTH_REQUEST, CookieUtils.serialize(authorizationRequest), COOKIE_EXPIRY_IN_SECONDS);

        String redirectedTo = request.getParameter(CookieUtils.COOKIE_NAME_REDIRECT_URL);
        if (StringUtils.isNotBlank(redirectedTo)) {
            CookieUtils.addCookie(response, CookieUtils.COOKIE_NAME_REDIRECT_URL, redirectedTo, COOKIE_EXPIRY_IN_SECONDS);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return loadAuthorizationRequest(request);
    }


    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        OAuth2AuthorizationRequest oAuth2AuthorizationRequest = removeAuthorizationRequest(request);

        CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_AUTH_REQUEST);
        //CookieUtils.deleteCookie(request, response, OAUTH_REDIRECT_URI);

        return oAuth2AuthorizationRequest;
    }

}
