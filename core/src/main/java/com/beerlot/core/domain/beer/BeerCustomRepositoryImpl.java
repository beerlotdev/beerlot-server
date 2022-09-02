package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.tag.Tag;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beerlot.core.domain.beer.QBeer.beer;
import static com.beerlot.core.domain.beer.QBeerTag.beerTag;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements BeerCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<Beer> findByKeywordAndTags(String keyword, List<Tag> tags) {
        return queryFactory
                .selectFrom(beer)
                .innerJoin(beerTag).on(beer.id.eq(beerTag.beer.id))
                .where(
                    containKeyword(keyword),
                    hasTags(tags)
                )
                .fetch();
    }

    private BooleanExpression containKeyword(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) {
            return null;
        }
        return beer.description.contains(keyword);
    }

    private BooleanExpression hasTags(List<Tag> tags) {
        for (Tag tag : tags) {
            if (beerTag.tag.id.equals(tag.getId())) {
                return Expressions.asBoolean(true).isTrue();
            }
        }
        return Expressions.asBoolean(false).isFalse();
    }
}
