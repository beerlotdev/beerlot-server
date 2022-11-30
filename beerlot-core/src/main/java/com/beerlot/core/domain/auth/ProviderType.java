package com.beerlot.core.domain.auth;

import lombok.Getter;

@Getter
public enum ProviderType {
    GOOGLE("google");

    private final String name;

    ProviderType(String name) {
        this.name = name;
    }
}
