package com.beerlot.domain.category.dto.response;

import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.common.entity.LanguageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategorySimpleResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @Builder
    public CategorySimpleResponse (Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategorySimpleResponse of(LanguageType language, Category category) {
        CategoryInternational categoryInternational = category.getCategoryInternationals().stream()
                .filter(ci -> ci.getId().getLanguage().equals(language))
                .findFirst().get();

        return CategorySimpleResponse.builder()
                .id(category.getId())
                .name(categoryInternational.getName())
                .build();
    }
}
