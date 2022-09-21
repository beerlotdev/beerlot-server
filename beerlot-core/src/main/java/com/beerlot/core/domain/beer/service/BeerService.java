package com.beerlot.core.domain.beer.service;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.repository.BeerRepository;
import com.beerlot.core.domain.beer.util.FindBeerResHelper;
import com.beerlot.core.domain.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public FindBeerResDto findBeerById(Long id) {
        return FindBeerResHelper.of(beerRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public Page<FindBeerResDto> findBeersBySearch(String keyword, List<Long> categoryIds, List<String> countries, List<Integer> volumes, int page, int size) {
        /*TODO: Change the parameter to List<Long> categoryIds*/
        Pageable pageable = (PageRequest) PageRequest.of(page, size);
        List<Country> parsedCountries = countries == null ? null : Country.valuesOf(countries);
        return beerRepository.findBySearch(keyword, categoryIds, parsedCountries, volumes, pageable);
    }
}
