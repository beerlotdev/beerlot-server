package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.domain.beer.util.FindBeerResHelper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<FindBeerResDto> findBySearch (String keyword, List<Long> categoryIds, List<Country> countries, List<Integer> volumes, Pageable pageable) {
        List<FindBeerResDto> findBeerResDtos = queryFactory
                .selectFrom(beer)
                .innerJoin(beer.beerTags, beerTag)
                .innerJoin(beer.category, category)
                .where(
                        hasKeyword(keyword),
                        hasCategories(categoryIds),
                        hasCountries(countries),
                        hasVolumes(volumes)
                )
                .limit(pageable.getPageSize())
                .offset((long) (pageable.getPageNumber() - 1) * (long)pageable.getPageSize())
                .fetch().stream().map(FindBeerResHelper::of).collect(Collectors.toList());

        return new PageImpl<>(findBeerResDtos, pageable, findBeerResDtos.size());
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
