package com.beerlot.domain.auth.util;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtils {
    private final static String HEADER_AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    private final static String TOKEN_PREFIX = "Bearer";

    public static String getAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_AUTHORIZATION);
        return removeTokenPrefix(bearerToken);
    }

    public static String getAccessToken(String bearerToken) {
        return removeTokenPrefix(bearerToken);
    }

    private static String removeTokenPrefix(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            String token = bearerToken.substring(TOKEN_PREFIX.length() + 1);
            return token;
        }
        return null;
    }
}