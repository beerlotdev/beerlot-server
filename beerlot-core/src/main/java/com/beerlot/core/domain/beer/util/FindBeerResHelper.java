package com.beerlot.core.domain.beer.util;

import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.category.util.FindCategoryResHelper;
import com.beerlot.core.domain.tag.util.FindTagResHelper;

import java.util.stream.Collectors;

public class FindBeerResHelper {

    public static FindBeerResDto of(Beer beer) {

        FindBeerResDto findBeerResDto = new FindBeerResDto();

        findBeerResDto.setId(beer.getId());
        findBeerResDto.setNameEn(beer.getNameEn());
        findBeerResDto.setNameKo(beer.getNameKo());
        findBeerResDto.setDescription(beer.getDescription());
        findBeerResDto.setCountry(FindCountryResHelper.of(beer.getCountry()));
        findBeerResDto.setVolume(beer.getVolume());
        findBeerResDto.setImageUrl(beer.getImageUrl());
        findBeerResDto.setCategory(FindCategoryResHelper.of(beer.getCategory()));
        findBeerResDto.setTags(beer.getTags().stream().map(FindTagResHelper::of).collect(Collectors.toList()));

        return findBeerResDto;
    }
}
