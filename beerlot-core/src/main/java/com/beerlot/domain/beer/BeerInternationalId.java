package com.beerlot.domain.beer;


import com.beerlot.domain.common.entity.LanguageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class BeerInternationalId implements Serializable {

    private Long beerId;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    public BeerInternationalId(Long beerId, LanguageType language) {
        this.beerId = beerId;
        this.language = language;
    }
}
