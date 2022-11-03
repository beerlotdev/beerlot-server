package com.beerlot.core.domain.beer.controller;

import com.beerlot.api.generated.api.BeerApi;
import com.beerlot.api.generated.api.BeerLikeApi;
import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.beer.service.BeerLikeService;
import com.beerlot.core.domain.beer.service.BeerService;
import com.beerlot.core.domain.beer.util.sort.BeerSortType;
import com.beerlot.core.domain.beer.util.page.BeerPage;
import com.beerlot.core.domain.common.page.PageCustom;
import com.beerlot.core.exception.ConflictException;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BeerController implements BeerApi, BeerLikeApi {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerLikeService beerLikeService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return BeerApi.super.getRequest();
    }

    @Override
    public ResponseEntity<BeerResponse> findBeerById(Long beerId) {
        return new ResponseEntity<>(beerService.findBeerById(beerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerPage> findBeersBySearch(Integer page, Integer size, BeerSortType sort, String keyword, List<Long> categories, List<String> countries, List<Integer> volumes) {
        PageCustom<BeerResponse> beerResponsePage = beerService.findBeersBySearch(keyword, categories, countries, volumes, page, size, sort);
        return new ResponseEntity<>(new BeerPage(beerResponsePage.getContents(), beerResponsePage.getPageRequest(), beerResponsePage.getTotalElements()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createBeerLike(Long beerId) {
        try {
            beerLikeService.likeBeer(beerId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<Void> deleteBeerLike(Long beerId) {
        try {
            beerLikeService.unlikeBeer(beerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<List<BeerResponse>> findTop10Beers() {
        return new ResponseEntity<>(beerService.findTop10Beers(), HttpStatus.OK);
    }
}
