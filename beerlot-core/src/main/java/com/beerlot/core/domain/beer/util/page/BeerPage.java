package com.beerlot.core.domain.beer.util.page;

import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.common.page.PageCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomImpl<BeerResponse> {
    public BeerPage(List<BeerResponse> contents, PageCustomRequest pageCustomRequest, long total) {
        super(contents, pageCustomRequest, total);
    }
}
