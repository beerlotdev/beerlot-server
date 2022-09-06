package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.beer.dto.BeerResDto;
import com.beerlot.core.domain.category.Category;

import java.util.List;

public interface BeerCustomRepository {
    List<BeerResDto> findBySearch(String keyword, List<Category> categories, List<Country> countries, List<Integer> volumes);
}
