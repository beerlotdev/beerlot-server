package com.beerlot.domain.beer.dto.response;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embedded;

public class BeerResponse {

    @Embedded
    private BeerSimpleResponse beerSimpleResponse;

    @JsonProperty("description")
    private String description;

    @JsonProperty("brewery")
    private String brewery;

    @JsonProperty("origin_city")
    private String originCity;

    @JsonProperty("volume")
    private Float volume;

    @Column(name = "calorie")
    private Integer calorie;

    @Column(name = "calorie_unit")
    private Integer calorieUnit;

    @JsonProperty("like_count")
    private Long likeCount;

    @JsonProperty("review_count")
    private Long reviewCount;

    @JsonProperty("rate")
    private Float rate;

    @Builder
    public BeerResponse(Long id, String name, String description, String originCountry, String originCity,
                        Float volume, String imageUrl, Long likeCount, Long reviewCount,
                        Float rate) {
        this.beerSimpleResponse = BeerSimpleResponse.builder()
                .id(id)
                .name(name)
                .originCountry(originCountry)
                .imageUrl(imageUrl)
                .build();
        this.description = description;
        this.originCity = originCity;
        this.volume = volume;
        this.likeCount = likeCount;
        this.reviewCount = reviewCount;
        this.rate = rate;
    }

    public static BeerResponse of(Beer beer, BeerInternational beerInternational) {
        return BeerResponse.builder()
                .id(beer.getId())
                .name(beerInternational.getName())
                .description(beerInternational.getDescription())
                .originCountry(beerInternational.getOriginCountry())
                .originCity(beerInternational.getOriginCity())
                .volume(beer.getVolume())
                .imageUrl(beer.getImageUrl())
                .likeCount(beer.getLikeCount())
                .reviewCount(beer.getReviewCount())
                .rate(beer.getRate())
                .build();
    }
}
