package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.dto.request.BeerSearchParam;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BeerCustomRepository {
    PageCustom<BeerResponse> findBySearch(BeerSearchParam beerSearchParam, LanguageType language, PageCustomRequest pageRequest);
}
