package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.dto.response.BeerSimpleResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomRequest;

import java.util.List;

public interface BeerCustomRepository {
    PageCustom<BeerSimpleResponse> findBySearch(String keyword,
                                                List<Long> categories,
                                                List<String> countries,
                                                Integer volumeMin,
                                                Integer volumeMax,
                                                LanguageType language, PageCustomRequest pageRequest);
}
