package com.beerlot.core.domain.member;

import com.beerlot.core.domain.auth.security.oauth.entity.ProviderType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MemberProfile {
    private Long id;

    private String email;

    private String username;

    private ProviderType provider;

    private String statusMessage;

    private Set<RoleType> roles;
}
