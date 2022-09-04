package com.beerlot.core.domain.beer.dto;

import com.beerlot.core.common.BaseResDto;
import com.beerlot.core.domain.category.dto.CategoryResDto;

public class BeerResDto extends BaseResDto {

    private String nameEn;

    private String nameKo;

    private String description;

    private CountryResDto origin;

    private Integer volume;

    private String imageUrl;

    private CategoryResDto category;
}
