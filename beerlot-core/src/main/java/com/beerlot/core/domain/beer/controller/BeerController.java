package com.beerlot.core.domain.beer.controller;

import com.beerlot.core.domain.beer.BeerSortType;
import com.beerlot.core.domain.beer.dto.response.BeerPage;
import com.beerlot.core.domain.beer.dto.response.BeerResponse;
import com.beerlot.core.domain.beer.service.BeerLikeService;
import com.beerlot.core.domain.beer.service.BeerService;
import com.beerlot.core.domain.common.entity.LanguageType;
import com.beerlot.core.domain.common.page.PageCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BeerController implements BeerApi, BeerLikeApi {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerLikeService beerLikeService;


    @Override
    public ResponseEntity<BeerResponse> findBeerById(Long beerId, LanguageType language) {
        LanguageType.validate(language);
        return new ResponseEntity<>(beerService.findBeerByIdAndLanguage(beerId, language), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BeerResponse>> findTop10Beers(LanguageType language) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerPage> findBeersBySearch(LanguageType language, Integer page, Integer size, BeerSortType sort, String keyword, List<Long> categories, List<String> countries, List<Integer> volumes) {
        LanguageType.validate(language);
        PageCustom<BeerResponse> beerResponsePage = beerService.findBeersBySearch(language, keyword, categories, countries, volumes, page, size, sort);
        if (beerResponsePage.getContents().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BeerPage(beerResponsePage.getContents(), beerResponsePage.getPageRequest(), beerResponsePage.getTotalElements()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createBeerLike(Long beerId) {
        beerLikeService.likeBeer(beerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteBeerLike(Long beerId) {
        beerLikeService.unlikeBeer(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
