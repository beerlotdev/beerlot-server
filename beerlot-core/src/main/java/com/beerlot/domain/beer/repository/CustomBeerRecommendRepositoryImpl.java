package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.Beer;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.beerlot.domain.beer.QBeer.*;
import static com.beerlot.domain.beer.QBeerLike.*;
import static com.beerlot.domain.review.QReview.*;

@Repository
@RequiredArgsConstructor
public class CustomBeerRecommendRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public List<Beer> getRecommendBeer(List<Dummy> dummies, Long userId) {
        List<Beer> result = new ArrayList<>();

        for (Dummy dummy : dummies) {
            result.addAll(getQuery(dummy, userId));
        }

        return result;
    }

    private List<Beer> getQuery(Dummy dummy, Long userId) {
        return queryFactory.selectFrom(beer)
                .where(beer.category.id.eq(dummy.getCategoryId()))
                .where(beer.id.notIn(
                        JPAExpressions.select(beer.id)
                                .from(beerLike)
                                .where(beerLike.member.id.ne(userId))))
                .where(beer.id.notIn(
                        JPAExpressions.select(beer.id)
                                .from(review)
                                .where(review.member.id.eq(userId))
                ))
                .orderBy(beer.rate.desc())
                .limit(dummy.amount)
                .fetch();
    }

    @Getter
    static class Dummy {
        long categoryId;

        int amount;
    }

}
