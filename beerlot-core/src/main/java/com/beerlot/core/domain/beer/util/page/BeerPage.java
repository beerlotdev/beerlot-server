package com.beerlot.core.domain.beer.util.page;

import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.common.page.PageCustomCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomCustomImpl<BeerResponse> {
    public BeerPage(List<BeerResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
