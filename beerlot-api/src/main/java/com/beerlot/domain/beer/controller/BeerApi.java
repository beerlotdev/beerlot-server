package com.beerlot.domain.beer.controller;

import com.beerlot.domain.beer.BeerSortType;
import com.beerlot.domain.beer.dto.response.BeerPage;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/v1/beers")
public interface BeerApi {

    @GetMapping("/{beerId}")
    ResponseEntity<BeerResponse> findBeerById(@PathVariable("beerId") Long beerId,
                                              @RequestParam("language") String language);

    @GetMapping("/top")
    ResponseEntity<List<BeerResponse>> findTop10Beers(@RequestParam("language") LanguageType language);

    @GetMapping
    ResponseEntity<BeerPage> findBeersBySearch(@RequestParam("language") LanguageType language,
                                               @RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "size") Integer size,
                                               @RequestParam(value = "sort") BeerSortType sort,
                                               @RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "categories", required = false) List<Long> categories,
                                               @RequestParam(value = "countries", required = false) List<String> countries,
                                               @RequestParam(value = "volumes", required = false) List<Integer> volumes);
}
