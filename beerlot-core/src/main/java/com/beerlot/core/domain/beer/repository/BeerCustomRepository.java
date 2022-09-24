package com.beerlot.core.domain.beer.repository;

import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.common.Page;
import com.beerlot.core.domain.common.PageCustomRequest;

import java.util.List;

public interface BeerCustomRepository {
    Page<FindBeerResDto> findBySearch(String keyword, List<Long> categoryIds, List<Country> countries, List<Integer> volumes, PageCustomRequest pageRequest);
}
