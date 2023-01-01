package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.QBeer;
import com.beerlot.domain.beer.dto.request.BeerSearchParam;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beerlot.domain.beer.QBeer.beer;
import static com.beerlot.domain.beer.QBeerInternational.beerInternational;
import static com.beerlot.domain.category.QCategory.category;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements BeerCustomRepository {
    private final JPAQueryFactory queryFactory;
    public PageCustom<BeerResponse> findBySearch (BeerSearchParam beerSearchParam, LanguageType language, PageCustomRequest pageRequest) {
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
                .innerJoin(beer.category, category)
                .where(
                        matchLanguage(language),
                        hasKeyword(beerSearchParam.getKeyword()),
                        hasCategories(beerSearchParam.getCategories()),
                        hasCountries(beerSearchParam.getCountries()),
                        betweenVolumes(beerSearchParam.getVolumeMin(), beerSearchParam.getVolumeMax())
                );

        long totalElements = query.fetch().size();

        List<BeerResponse> beerResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy(QBeer.class))
                .fetch();

        return new PageCustomImpl<>(beerResponseList, pageRequest, totalElements);
    }

    private BooleanExpression matchLanguage(LanguageType language) {
        return beerInternational.id.language.stringValue().eq(language.toString());
    }

    private BooleanExpression hasKeyword(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) {
            return null;
        }
        return beerInternational.description.contains(keyword)
                .or(beerInternational.originCountry.contains(keyword));
    }

    private BooleanExpression hasCategories(List<Long> categoryIds) {
        if (categoryIds == null) return null;
        return beer.category.id.in(categoryIds);
    }

    private BooleanExpression hasCountries(List<String> countries) {
        if (countries == null) return null;
        return beerInternational.originCountry.in(countries);
    }

    private BooleanExpression betweenVolumes(Integer volumeMin, Integer volumeMax) {
        if (volumeMin == null && volumeMax == null) return null;
        Integer _volumeMin = volumeMin == null ? 0 : volumeMin;
        Integer _volumeMax = volumeMax == null ? 100 : volumeMin;
        return beer.volume.castToNum(Integer.class).between(_volumeMin, _volumeMax);
    }
}
