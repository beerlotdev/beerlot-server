package com.beerlot.domain.beer.dto.request;

import com.beerlot.domain.beer.BeerSortType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BeerSearchParam {

    @JsonProperty(value = "keyword")
    String keyword;

    @JsonProperty(value = "categories")
    List<Long> categories;

    @JsonProperty(value = "countries")
    List<String> countries;

    @JsonProperty(value = "volumeMin")
    Integer volumeMin;

    @JsonProperty(value = "volumeMax")
    Integer volumeMax;

    @JsonProperty(value = "page", required = true)
    Integer page;

    @JsonProperty(value = "size", required = true)
    Integer size;

    @JsonProperty(value = "sort", required = true)
    BeerSortType sort;
}
