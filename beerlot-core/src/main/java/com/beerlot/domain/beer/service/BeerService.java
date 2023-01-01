package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.beer.BeerInternationalId;
import com.beerlot.domain.beer.BeerSortType;
import com.beerlot.domain.beer.dto.request.BeerSearchParam;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.beer.repository.BeerInternationalRepository;
import com.beerlot.domain.beer.repository.BeerRepository;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.exception.ErrorMessage;
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
    public PageCustom<BeerResponse> findBeersBySearch(BeerSearchParam beerSearchParam, LanguageType language, PageCustomRequest pageRequest) {
        return beerRepository.findBySearch(beerSearchParam, language, pageRequest);
    }

    @Transactional(readOnly = true)
    public Beer findBeerById(Long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.BEER__NOT_EXIST.getMessage()));
    }

    @Transactional(readOnly = true)
    private BeerInternational findBeerInternationalByKey(Long id, LanguageType language) {
        return beerInternationalRepository.findById(new BeerInternationalId(id, language))
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.BEER_INTERNATIONAL__NOT_EXIST.getMessage()));
    }
}
