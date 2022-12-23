package com.beerlot.core.domain.common.entity;

import com.beerlot.core.exception.ErrorMessage;

import java.util.Arrays;


public enum LanguageType {
    KR,
    EN;

    public static void validate(LanguageType language) {
        Arrays.stream(LanguageType.values()).filter(l -> l.equals(language)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.LANGUAGE_INVALID.getMessage()));
    }
}
