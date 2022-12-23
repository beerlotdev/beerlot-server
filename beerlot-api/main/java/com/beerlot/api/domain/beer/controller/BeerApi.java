package com.beerlot.api.domain.beer.controller;

import java.util.List;

public interface BeerApi {
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
