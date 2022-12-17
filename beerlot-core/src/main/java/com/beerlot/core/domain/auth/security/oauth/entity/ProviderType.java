package com.beerlot.core.domain.auth.security.oauth.entity;

import com.beerlot.core.domain.member.RoleType;
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
                .roles(Set.of(RoleType.ROLE_GUEST))
                .build();
        return userPrincipal;
    });

    private final String registrationId;
    private final Function<Map<String, Object>, OAuthUserPrincipal> oAuthUserPrincipal;

    ProviderType(String registrationId, Function<Map<String, Object>, OAuthUserPrincipal> oAuthUserPrincipal) {
        this.registrationId = registrationId;
        this.oAuthUserPrincipal = oAuthUserPrincipal;
    }

    public static OAuthUserPrincipal getOAuthUser(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .oAuthUserPrincipal.apply(attributes);
    }

}
