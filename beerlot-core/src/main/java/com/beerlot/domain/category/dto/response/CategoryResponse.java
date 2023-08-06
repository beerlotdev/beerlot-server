package com.beerlot.domain.category.dto.response;

import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.common.entity.LanguageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class CategoryResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("children")
    private List<CategoryResponse> children;

    @Builder
    public CategoryResponse(Long id, String name, List<CategoryResponse> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public static CategoryResponse of(LanguageType language, Category category) {
        Optional<CategoryInternational> maybeInternational = category.getCategoryInternationals()
                .stream()
                .filter(o -> o.getId().getLanguage().equals(language))
                .findFirst();

        if (maybeInternational.isEmpty()) {
            return null;
        }

        CategoryInternational categoryInternational = maybeInternational.get();

        ArrayList<CategoryResponse> responses = new ArrayList<>();
        if (!category.getChildren().isEmpty()) {
            for (Category x : category.getChildren()) {
                responses.add(CategoryResponse.of(language, x));
            }
        }

        return CategoryResponse.builder()
                .id(category.getId())
                .name(categoryInternational.getName())
                .children(responses)
                .build();
    }
}
