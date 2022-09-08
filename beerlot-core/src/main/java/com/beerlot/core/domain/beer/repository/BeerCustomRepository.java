package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerCustomRepository {
    Page<FindBeerResDto> findBySearch(String keyword, List<Long> categoryIds, List<Country> countries, List<Integer> volumes, Pageable pageable);
}
