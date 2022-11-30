package com.beerlot.core.domain.auth.oauth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Component("GoogleOAuthProvider")
public class GoogleOAuthProvider implements OAuthProvider {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${oauth.client.google.oauth-login-endpoint}")
    private String oAuthLoginEndpoint;
    @Value("${oauth.client.google.oauth-token-endpoint}")
    private String oAuthTokenEndpoint;
    @Value("${oauth.client.google.client-id}")
    private String clientId;
    @Value("${oauth.client.google.client-secret}")
    private String clientSecret;
    @Value("${oauth.client.google.state}")
    private String state;
    @Value("${oauth.client.google.scope}")
    private String scope;
    @Value("${oauth.client.google.backend-redirect-url}")
    private String backendRedirectUrl;
    @Value("${oauth.client.google.response_type}")
    private String responseType;
    @Value("${oauth.client.google.grant_type}")
    private String grantType;
    @Value("${jwt.header}")
    private String jwtHeader;

    @Override
    public String buildLoginRequestUrl() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", clientId);
        parameters.put("state", state);
        parameters.put("redirect_uri", backendRedirectUrl);
        parameters.put("scope", scope);
        parameters.put("response_type", responseType);

        String parametersInline = parameters.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return oAuthLoginEndpoint + "?" + parametersInline;
    }

    @Override
    public GoogleOAuthTokenResponse requestToken(String code) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("code", code);
        parameters.put("client_id", clientId);
        parameters.put("client_secret", clientSecret);
        parameters.put("redirect_uri", backendRedirectUrl);
        parameters.put("grant_type", grantType);

        GoogleOAuthTokenResponse googleOAuthTokenResponse = restTemplate.postForObject(oAuthTokenEndpoint, parameters, GoogleOAuthTokenResponse.class);
        return googleOAuthTokenResponse;
    }

    @Override
    public GoogleOAuthUser requestUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(jwtHeader, "Bearer " + accessToken);

        HttpEntity<Map<String, String>> headerEntity = new HttpEntity(httpHeaders);

        String url = "https://www.googleapis.com/oauth2/v2/userinfo";

        GoogleOAuthUser googleOAuthUser = restTemplate.exchange(url, HttpMethod.GET, headerEntity, GoogleOAuthUser.class).getBody();
        return googleOAuthUser;
    }
}
