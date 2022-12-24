package com.beerlot.domain.auth.security.oauth.entity;

import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class OAuthUserPrincipal implements OAuth2User {
    private String id;
    private String email;
    private String username;
    private String imageUrl;
    private ProviderType provider;
    private Set<RoleType> roles;

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", this.id);
        attributes.put("email", this.email);
        attributes.put("username", this.username);
        attributes.put("image_url", this.imageUrl);
        attributes.put("provider", this.provider);
        attributes.put("roles", this.roles);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.toString())).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return this.username;
    }

    public String getOauthId() {
        return id;
    }

    @Builder
    public OAuthUserPrincipal(String id, String email, String username, String imageUrl, String provider, Set<RoleType> roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.imageUrl = imageUrl;
        this.provider = ProviderType.valueOf(provider.toUpperCase());
        this.roles = roles;
    }

    public static OAuthUserPrincipal of(Member member) {
        return OAuthUserPrincipal.builder()
                .id(member.getOauthId())
                .email(member.getEmail())
                .username(member.getUsername())
                .imageUrl(member.getImageUrl())
                .provider(member.getProvider().toString())
                .roles(member.getRoles())
                .build();
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
