package com.beerlot.domain.beer.dto.response;

import com.beerlot.domain.common.page.PageCustomCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomCustomImpl<BeerResponse> {
    public BeerPage(List<BeerResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
