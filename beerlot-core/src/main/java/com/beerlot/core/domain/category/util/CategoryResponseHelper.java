package com.beerlot.core.domain.category.util;

import com.beerlot.api.generated.model.CategorySupResponse;
import com.beerlot.core.domain.category.Category;

public class CategoryResponseHelper {

    public static CategorySupResponse of(Category category){

        CategorySupResponse categorySupResponse = new CategorySupResponse();

        categorySupResponse.setId(category.getId());
        categorySupResponse.setNameKo(category.getNameKo());
        categorySupResponse.setNameEn(category.getNameEn());
        categorySupResponse.setDescription(category.getDescription());
        categorySupResponse.setParent(category.getParent() == null ? null : CategoryResponseHelper.of(category.getParent()));

        return categorySupResponse;
    }
}
