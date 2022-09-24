package com.beerlot.core.domain.beer.util.page;

import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.common.PageCustomImpl;
import com.beerlot.core.domain.common.PageCustomRequest;

import java.util.List;

public class BeerPage extends PageCustomImpl<FindBeerResDto> {
    public BeerPage(List<FindBeerResDto> contents, PageCustomRequest pageCustomRequest, long total) {
        super(contents, pageCustomRequest, total);
    }
}
