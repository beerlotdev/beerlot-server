package com.beerlot.tool.fixture;

import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.common.entity.LanguageType;

public class CategoryFixture {
    public static CategoryInternational createCategoryInternational() {
        return CategoryInternational.builder()
                .name("에일")
                .description("에일 맥주는 풍부한 바디감과 목넘김이 부드럽다는 특징이 있습니다. 쌉싸름한 맛, 과일향 등 다양한 재료와의 조합이 돋보입니다.")
                .language(LanguageType.KR)
                .category(createCategory())
                .build();
    }

    public static Category createCategory() {
        return Category.builder()
                .id(1L)
                .parent(null)
                .build();
    }
}
