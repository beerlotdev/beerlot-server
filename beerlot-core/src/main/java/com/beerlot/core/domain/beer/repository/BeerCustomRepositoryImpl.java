package com.beerlot.core.domain.beer.repository;

import com.beerlot.api.generated.model.BeerResponse;
import com.beerlot.core.domain.common.entity.LanguageType;
import com.beerlot.core.domain.common.page.PageCustom;
import com.beerlot.core.domain.common.page.PageCustomCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beerlot.core.domain.beer.QBeer.beer;
import static com.beerlot.core.domain.beer.QBeerInternational.beerInternational;
import static com.beerlot.core.domain.category.QCategory.category;
import static com.beerlot.core.domain.tag.QBeerTag.beerTag;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements BeerCustomRepository {
    private final JPAQueryFactory queryFactory;
    public PageCustom<BeerResponse> findBySearch (LanguageType language, String keyword, List<Long> categoryIds, List<String> countries, List<Integer> volumes, PageCustomRequest pageRequest) {
        JPAQuery<BeerResponse> query = queryFactory
                .select(Projections.fields(BeerResponse.class,
                        beer.id,
                        beerInternational.name,
                        beerInternational.originCountry,
                        beer.volume,
                        beer.imageUrl
                        ))
                .from(beer)
                .innerJoin(beer.beerInternationals, beerInternational)
                .innerJoin(beer.beerTags, beerTag)
                .innerJoin(beer.category, category)
                .where(
                        matchLanguage(language),
                        hasKeyword(keyword),
                        hasCategories(categoryIds),
                        hasCountries(countries),
                        hasVolumes(volumes)
                );

        long totalElements = query.fetch().size();

        List<BeerResponse> beerResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy())
                .fetch();

        return new PageCustomCustomImpl<>(beerResponseList, pageRequest, totalElements);
    }

    private BooleanExpression matchLanguage(LanguageType language) {
        return beerInternational.language.stringValue().eq(language.toString());
    }

    private BooleanExpression hasKeyword(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) {
            return null;
        }
        return beerInternational.description.contains(keyword)
                .or(beerInternational.originCountry.contains(keyword))
                .or(beer.category.description.contains(keyword));
    }

    private BooleanExpression hasCategories(List<Long> categoryIds) {
        if (categoryIds == null) return null;
        return beer.category.id.in(categoryIds);
    }

    private BooleanExpression hasCountries(List<String> countries) {
        if (countries == null) return null;
        return beerInternational.originCountry.in(countries);
    }

    private BooleanExpression hasVolumes(List<Integer> volumes) {
        if (volumes == null) return null;
        return beer.volume.castToNum(Integer.class).in(volumes);
    }
}
