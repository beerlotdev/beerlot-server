package com.beerlot.domain.beer.dto.response;

import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomImpl<BeerSimpleResponse> {
    public BeerPage(List<BeerSimpleResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
