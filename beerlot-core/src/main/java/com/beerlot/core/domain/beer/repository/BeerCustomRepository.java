package com.beerlot.core.domain.beer.repository;

import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.common.page.Page;
import com.beerlot.core.domain.common.page.PageCustomRequest;

import java.util.List;

public interface BeerCustomRepository {
    Page<BeerResponse> findBySearch(String keyword, List<Long> categoryIds, List<Country> countries, List<Integer> volumes, PageCustomRequest pageRequest);
}
