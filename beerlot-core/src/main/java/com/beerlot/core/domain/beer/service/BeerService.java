package com.beerlot.core.domain.beer.service;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.BeerInternational;
import com.beerlot.core.domain.beer.BeerInternationalId;
import com.beerlot.core.domain.beer.BeerSortType;
import com.beerlot.core.domain.beer.dto.response.BeerResponse;
import com.beerlot.core.domain.beer.repository.BeerInternationalRepository;
import com.beerlot.core.domain.beer.repository.BeerRepository;
import com.beerlot.core.domain.common.entity.LanguageType;
import com.beerlot.core.domain.common.page.PageCustom;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import com.beerlot.core.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerInternationalRepository beerInternationalRepository;

    @Transactional(readOnly = true)
    public BeerResponse findBeerByIdAndLanguage(Long id, LanguageType language) {
        Beer beer = findBeerById(id);
        BeerInternational beerInternational = findBeerInternationalByKey(id, language);
        return BeerResponse.of(beer, beerInternational);
    }

    // TODO: Implement BeerSimpleResponse.of and map
    /*public List<BeerSimpleResponse> findTop10Beers() {
        List<BeerSimpleResponse> beerResponseList = beerRepository.findTop10ByOrderByLikeCountDesc().stream().map(BeerResponse::of).collect(Collectors.toList());
        return beerResponseList;
    }*/

    @Transactional(readOnly = true)
    public PageCustom<BeerResponse> findBeersBySearch(LanguageType language, String keyword, List<Long> categoryIds, List<String> countries, List<Integer> volumes, int page, int size, BeerSortType sort) {
        PageCustomRequest pageRequest = new PageCustomRequest(page, size, sort);
        return beerRepository.findBySearch(language, keyword, categoryIds, countries, volumes, pageRequest);
    }

    @Transactional(readOnly = true)
    private Beer findBeerById(Long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.BEER__NOT_EXIST.getMessage()));
    }

    @Transactional(readOnly = true)
    private BeerInternational findBeerInternationalByKey(Long id, LanguageType language) {
        return beerInternationalRepository.findById(new BeerInternationalId(id, language))
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.BEER_INTERNATIONAL__NOT_EXIST.getMessage()));
    }
}
