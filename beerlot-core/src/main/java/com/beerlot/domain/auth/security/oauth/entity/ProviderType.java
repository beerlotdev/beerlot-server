package com.beerlot.domain.auth.security.oauth.entity;

import com.beerlot.domain.member.RoleType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Getter
public enum ProviderType {
    GOOGLE("google", (attributes) -> {
        OAuthUserPrincipal userPrincipal = OAuthUserPrincipal.builder()
                .id(attributes.get("sub").toString())
                .email(attributes.get("email").toString())
                .username(attributes.get("name").toString())
                .imageUrl(attributes.get("picture").toString())
                .provider("google")
                .roles(Set.of(RoleType.GUEST))
                .build();
        return userPrincipal;
    }),

    NAVER("naver", (attributes) -> {
        OAuthUserPrincipal userPrincipal = OAuthUserPrincipal.builder()
                .id(attributes.get("id").toString())
                .email(attributes.get("email").toString())
                .username(attributes.get("name").toString())
                .imageUrl(attributes.get("profile_image").toString())
                .provider("naver")
                .roles(Set.of(RoleType.GUEST))
                .build();
        return userPrincipal;
    });

    private final String registrationId;
    private final Function<Map<String, Object>, OAuthUserPrincipal> oAuthUserPrincipal;

    ProviderType(String registrationId, Function<Map<String, Object>, OAuthUserPrincipal> oAuthUserPrincipal) {
        this.registrationId = registrationId;
        this.oAuthUserPrincipal = oAuthUserPrincipal;
    }

    public static OAuthUserPrincipal getOAuthUser(String registrationId, Map<String, Object> attributes, String nameAttributeKey) {
        if (registrationId.equals("naver")) {
            attributes = (Map<String, Object>) attributes.get(nameAttributeKey);
        }

        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .oAuthUserPrincipal.apply(attributes);
    }

}
