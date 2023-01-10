package com.beerlot.domain.glassware;

import com.beerlot.domain.common.entity.LanguageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class GlasswareInternationalId {

    private Long glasswareId;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    public GlasswareInternationalId(Long glasswareId, LanguageType language) {
        this.glasswareId = glasswareId;
        this.language = language;
    }
}
