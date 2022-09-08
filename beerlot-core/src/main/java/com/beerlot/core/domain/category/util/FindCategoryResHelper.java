package com.beerlot.core.domain.category.util;

import com.beerlot.api.generated.model.FindCategorySupResDto;
import com.beerlot.core.domain.category.Category;

public class FindCategoryResHelper {

    public static FindCategorySupResDto of(Category category){

        FindCategorySupResDto findCategorySupResDto = new FindCategorySupResDto();

        findCategorySupResDto.setId(category.getId());
        findCategorySupResDto.setNameKo(category.getNameKo());
        findCategorySupResDto.setNameEn(category.getNameEn());
        findCategorySupResDto.setDescription(category.getDescription());
        findCategorySupResDto.setParent(category.getParent() == null ? null : FindCategoryResHelper.of(category.getParent()));

        return findCategorySupResDto;
    }
}
