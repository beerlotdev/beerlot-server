package com.beerlot.domain.common.entity;

import com.beerlot.exception.ErrorMessage;

import java.util.Arrays;
import java.util.Optional;


public enum LanguageType {
    KR,
    EN;

    public static void validate(LanguageType language) {
        Arrays.stream(LanguageType.values()).filter(l -> l.equals(language)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.LANGUAGE_INVALID.getMessage()));
    }

    public static LanguageType toEnum (String language) {
        try {
            return LanguageType.valueOf(language);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.LANGUAGE_INVALID.getMessage());
        }
    }
}
