package com.beerlot.core.domain.beer.controller;

import com.beerlot.api.generated.api.BeerApi;
import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.service.BeerService;

import com.beerlot.core.domain.beer.util.page.BeerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeerController implements BeerApi {

    @Autowired
    private BeerService beerService;

    @Override
    public ResponseEntity<FindBeerResDto> findBeerById(Long beerId) {
        return new ResponseEntity<>(beerService.findBeerById(beerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerPage> findBeersBySearch(Integer page, Integer size, String keyword, List<Long> categories, List<String> countries, List<Integer> volumes) {
        Page<FindBeerResDto> findBeerResDtoPage = beerService.findBeersBySearch(keyword, categories, countries, volumes, page, size);
        return new ResponseEntity<>(new BeerPage(findBeerResDtoPage.getContent(), findBeerResDtoPage.getPageable(), findBeerResDtoPage.getContent().size()), HttpStatus.OK);
    }
}
