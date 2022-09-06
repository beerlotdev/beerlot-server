package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.beer.dto.BeerResDto;
import com.beerlot.core.domain.category.Category;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.beerlot.core.domain.beer.QBeer.beer;
import static com.beerlot.core.domain.category.QCategory.category;
import static com.beerlot.core.domain.tag.QBeerTag.beerTag;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements BeerCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<BeerResDto> findBySearch (String keyword, List<Category> categories, List<Country> countries, List<Integer> volumes) {
        return queryFactory
                .selectFrom(beer)
                .innerJoin(beer.beerTags, beerTag)
                .innerJoin(beer.category, category)
                .where(
                        hasKeyword(keyword),
                        hasCategories(categories),
                        hasCountries(countries),
                        hasVolumes(volumes)
                )
                .fetch().stream().map(BeerResDto::of).collect(Collectors.toList());
    }

    private BooleanExpression hasKeyword(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) {
            return null;
        }
        return beer.description.contains(keyword)
                .or(beer.category.description.contains(keyword))
                .or(beer.beerTags.any().tag.description.contains(keyword));
    }

    private BooleanExpression hasCategories(List<Category> categories) {
        if (categories == null) return null;
        return beer.category.in(categories);
    }

    private BooleanExpression hasCountries(List<Country> countries) {
        if (countries == null) return null;
        return beer.origin.in(countries);
    }

    private BooleanExpression hasVolumes(List<Integer> volumes) {
        if (volumes == null) return null;
        BooleanExpression be = beer.volume.castToNum(Integer.class).in(volumes);
        return be;
    }
}
