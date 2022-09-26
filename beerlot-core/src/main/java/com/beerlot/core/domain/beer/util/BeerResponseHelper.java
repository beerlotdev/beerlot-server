package com.beerlot.core.domain.beer.util;

import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.category.util.CategoryResponseHelper;
import com.beerlot.core.domain.tag.util.TagResponseHelper;

import java.util.stream.Collectors;

public class BeerResponseHelper {

    public static BeerResponse of(Beer beer) {

        BeerResponse beerResponse = new BeerResponse();

        beerResponse.setId(beer.getId());
        beerResponse.setNameEn(beer.getNameEn());
        beerResponse.setNameKo(beer.getNameKo());
        beerResponse.setDescription(beer.getDescription());
        beerResponse.setCountry(CountryResponseHelper.of(beer.getCountry()));
        beerResponse.setVolume(beer.getVolume());
        beerResponse.setImageUrl(beer.getImageUrl());
        beerResponse.setCategory(CategoryResponseHelper.of(beer.getCategory()));
        beerResponse.setTags(beer.getTags().stream().map(TagResponseHelper::of).collect(Collectors.toList()));
        beerResponse.setLikeCount(beer.getLikeCount());

        return beerResponse;
    }
}
