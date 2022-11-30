package com.beerlot.core.domain.member;

public enum RoleType {
    GENERAL("ROLE_GENERAL"),
    ADMIN("ROLE_ADMIN");

    private final String code;

    RoleType(String code) {
        this.code = code;
    }
}
