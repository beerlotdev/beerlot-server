package com.beerlot.core.domain.beer.controller;

import com.beerlot.api.generated.api.BeerApi;
import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.service.BeerService;
import com.beerlot.core.domain.beer.util.page.BeerPage;
import com.beerlot.core.domain.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BeerController implements BeerApi {

    @Autowired
    private BeerService beerService;

    @Override
    public ResponseEntity<BeerResponse> findBeerById(Long beerId) {
        return new ResponseEntity<>(beerService.findBeerById(beerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerPage> findBeersBySearch(Integer page, Integer size, String keyword, List<Long> categories, List<String> countries, List<Integer> volumes) {
        Page<BeerResponse> beerResponsePage = beerService.findBeersBySearch(keyword, categories, countries, volumes, page, size);
        return new ResponseEntity<>(new BeerPage(beerResponsePage.getContents(), beerResponsePage.getPageRequest(), beerResponsePage.getContents().size()), HttpStatus.OK);
    }
}
