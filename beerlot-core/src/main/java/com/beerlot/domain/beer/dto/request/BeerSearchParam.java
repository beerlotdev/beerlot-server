package com.beerlot.domain.beer.dto.request;

import com.beerlot.domain.beer.BeerSortType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BeerSearchParam {

    @JsonProperty(value = "keyword")
    String keyword;

    @JsonProperty(value = "categories")
    List<Long> categories;

    @JsonProperty(value = "countries")
    List<String> countries;

    @JsonProperty(value = "volume_min", defaultValue = "0")
    Integer volumeMin;

    @JsonProperty(value = "volume_max", defaultValue = "100")
    Integer volumeMax;
}
