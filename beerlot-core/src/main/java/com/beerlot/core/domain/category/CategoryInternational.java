package com.beerlot.core.domain.category;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.common.entity.LanguageType;
import lombok.Builder;

import javax.persistence.*;

public class CategoryInternational {

    @EmbeddedId
    private CategoryInternationalId id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Beer beer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder
    public CategoryInternational(Category category, LanguageType language, String name, String description, String originCountry, String originCity) {
        this.id = new CategoryInternationalId(category.getId(), language);
        this.beer = beer;
        this.name = name;
        this.description = description;
    }
}
