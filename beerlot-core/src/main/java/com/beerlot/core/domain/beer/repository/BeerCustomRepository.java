package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.dto.response.BeerResponse;
import com.beerlot.core.domain.common.entity.LanguageType;
import com.beerlot.core.domain.common.page.PageCustom;
import com.beerlot.core.domain.common.page.PageCustomRequest;

import java.util.List;

public interface BeerCustomRepository {
    PageCustom<BeerResponse> findBySearch(LanguageType language, String keyword, List<Long> categoryIds, List<String> countries, List<Integer> volumes, PageCustomRequest pageRequest);
}
