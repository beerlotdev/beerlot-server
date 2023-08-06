package com.beerlot.tool.fixture;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.category.Category;
import com.beerlot.domain.common.entity.LanguageType;

import java.util.Arrays;
import java.util.Set;

public class BeerFixture {
    public static BeerInternational createBeerInternational() {
        return BeerInternational.builder()
                .name("호가든")
                .description("호가든 맥주입니다!")
                .originCountry("벨기에")
                .originCity("플람스브라반트")
                .language(LanguageType.KR)
                .beer(createBeer())
                .build();
    }

    public static Beer createBeer() {
        return Beer.builder()
                .id(1L)
                .imageUrl("https://beerlot.com/image_url")
                .volume(4.4f)
                .category(CategoryFixture.createCategory())
                .buyFrom(Set.of("편의점-GS편의점"))
                .build();
    }
}
