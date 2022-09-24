package com.beerlot.core.domain.beer.repository;

import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.beer.util.FindBeerResHelper;
import com.beerlot.core.domain.common.Page;
import com.beerlot.core.domain.common.PageCustomImpl;
import com.beerlot.core.domain.common.PageCustomRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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

    public Page<FindBeerResDto> findBySearch (String keyword, List<Long> categoryIds, List<Country> countries, List<Integer> volumes, PageCustomRequest pageRequest) {
        JPAQuery<Beer> query = queryFactory
                .selectFrom(beer)
                .innerJoin(beer.beerTags, beerTag)
                .innerJoin(beer.category, category)
                .where(
                        hasKeyword(keyword),
                        hasCategories(categoryIds),
                        hasCountries(countries),
                        hasVolumes(volumes)
                );

        long totalElements = query.fetch().size();

        List<FindBeerResDto> findBeerResDtos = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .fetch().stream().map(FindBeerResHelper::of).collect(Collectors.toList());

        return new PageCustomImpl<>(findBeerResDtos, pageRequest, totalElements);
    }

    private BooleanExpression hasKeyword(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) {
            return null;
        }
        return beer.description.contains(keyword)
                .or(beer.category.description.contains(keyword))
                .or(beer.beerTags.any().tag.description.contains(keyword));
    }

    private BooleanExpression hasCategories(List<Long> categoryIds) {
        if (categoryIds == null) return null;
        return beer.category.id.in(categoryIds);
    }

    private BooleanExpression hasCountries(List<Country> countries) {
        if (countries == null) return null;
        return beer.country.in(countries);
    }

    private BooleanExpression hasVolumes(List<Integer> volumes) {
        if (volumes == null) return null;
        return beer.volume.castToNum(Integer.class).in(volumes);
    }
}
