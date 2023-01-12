package com.beerlot.domain.beer.dto.response;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.dto.response.CategorySimpleResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class BeerSimpleResponse implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("origin_country")
    private String originCountry;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("category")
    private CategorySimpleResponse category;

    @Builder
    public BeerSimpleResponse(Long id, String name, String originCountry, String imageUrl, CategorySimpleResponse category) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public static BeerSimpleResponse of(LanguageType language, Beer beer) {
        BeerInternational beerInternational = beer.getBeerInternationals().stream()
                .filter(bi -> bi.getId().getLanguage().equals(language))
                .findFirst().get();

        return BeerSimpleResponse.builder()
                .id(beer.getId())
                .name(beerInternational.getName())
                .originCountry(beerInternational.getOriginCountry())
                .imageUrl(beer.getImageUrl())
                .category(CategorySimpleResponse.of(language, beer.getCategory()))
                .build();
    }

}
