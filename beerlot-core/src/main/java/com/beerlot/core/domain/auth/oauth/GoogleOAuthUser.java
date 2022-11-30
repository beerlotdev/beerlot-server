package com.beerlot.core.domain.auth.oauth;

import com.beerlot.core.domain.auth.ProviderType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GoogleOAuthUser extends OAuthUser {
    private String id;
    private String name;
    private String email;
    private String picture;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getImageUrl() {
        return picture;
    }

    @Override
    public ProviderType getProviderType() {
        return ProviderType.GOOGLE;
    }
}
