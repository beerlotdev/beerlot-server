package com.beerlot.core.domain.category;

import com.beerlot.core.domain.common.entity.LanguageType;
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
public class CategoryInternationalId implements Serializable {

    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    public CategoryInternationalId(Long categoryId, LanguageType language) {
        this.categoryId = categoryId;
        this.language = language;
    }
}
