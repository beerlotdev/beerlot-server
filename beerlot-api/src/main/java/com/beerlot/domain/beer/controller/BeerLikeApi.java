package com.beerlot.domain.beer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/beers")
public interface BeerLikeApi {

    @PostMapping("/{beerId}/likes")
    ResponseEntity<Void> createBeerLike(@PathVariable("beerId") Long beerId);

    @DeleteMapping("/{beerId}/likes")
    ResponseEntity<Void> deleteBeerLike(@PathVariable("beerId") Long beerId);
}