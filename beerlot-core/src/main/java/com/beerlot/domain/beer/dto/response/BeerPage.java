package com.beerlot.domain.beer.dto.response;

import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomImpl<BeerResponse> {
    public BeerPage(List<BeerResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
