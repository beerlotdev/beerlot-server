package com.beerlot.domain.beer.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class BeerRecommendResponse {

    private List<Long> id;

    public BeerRecommendResponse(List<Long> id) {
        this.id = id;
    }
}
