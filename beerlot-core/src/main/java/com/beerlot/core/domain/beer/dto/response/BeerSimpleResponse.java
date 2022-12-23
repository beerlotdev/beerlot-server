package com.beerlot.core.domain.beer.dto.response;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.BeerInternational;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
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
    private String category;

    @Builder
    public BeerSimpleResponse(Long id, String name, String originCountry, String imageUrl, String category) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public BeerSimpleResponse of(Beer beer, BeerInternational beerInternational) {
        return BeerSimpleResponse.builder()
                .id(beerInternational.getBeer().getId())
                .name(beerInternational.getName())
                .originCountry(beerInternational.getOriginCountry())
                .imageUrl(beer.getImageUrl())
                .build();
    }

}
