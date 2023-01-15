package com.beerlot.domain.review.repository;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.QBeer;
import com.beerlot.domain.beer.QBeerInternational;
import com.beerlot.domain.beer.dto.response.BeerSimpleResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.QMember;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.QReview;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.dto.response.ReviewArchiveResponse;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.beerlot.domain.beer.QBeer.beer;
import static com.beerlot.domain.beer.QBeerInternational.beerInternational;
import static com.beerlot.domain.member.QMember.member;
import static com.beerlot.domain.review.QReview.review;
import static com.querydsl.core.alias.Alias.$;
import static com.querydsl.core.alias.Alias.alias;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public PageCustom<ReviewResponse> findByBeerId(Long beerId, PageCustomRequest pageRequest) {
        QMember member1 = new QMember("m");
        QBeer beer1 = new QBeer("b");

        JPAQuery<ReviewResponse> query = queryFactory
                .select(Projections.fields(ReviewResponse.class,
                            review.id,
                            review.content,
                            review.imageUrl,
                            review.rate,
                            review.updatedAt,
                            review.likeCount,
                            Projections.fields(MemberResponse.class,
                                    member1.id,
                                    member1.username,
                                    member1.imageUrl
                            ).as("member")
                        )
                )
                .from(review)
                .innerJoin(review.beer, beer1)
                .innerJoin(review.member, member1)
                .where(
                        beer1.id.eq(beerId)
                );

        long totalElements = query.fetch().size();

        List<ReviewResponse> reviewResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy(QReview.class, review.getMetadata().getName()))
                .fetch();

        return new PageCustomImpl<>(reviewResponseList, pageRequest, totalElements);
    }

    @Override
    public PageCustom<ReviewResponse> findAll(PageCustomRequest pageRequest) {
        JPAQuery<ReviewResponse> query = queryFactory
                .select(Projections.fields(ReviewResponse.class,
                                review.id,
                                review.content,
                                review.imageUrl,
                                review.rate,
                                review.updatedAt,
                                review.likeCount,
                                Projections.fields(MemberResponse.class,
                                        review.member.id,
                                        review.member.username,
                                        review.member.imageUrl
                                ).as("member")
                        )
                )
                .from(review)
                .innerJoin(review.beer, beer);

        long totalElements = query.fetch().size();

        List<ReviewResponse> reviewResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy(QReview.class, review.getMetadata().getName()))
                .fetch();

        return new PageCustomImpl<>(reviewResponseList, pageRequest, totalElements);
    }

    @Override
    public PageCustom<ReviewArchiveResponse> findByMember(String oauthId,
                                                          PageCustomRequest pageRequest,
                                                          LanguageType language) {
        JPAQuery<ReviewArchiveResponse> query = queryFactory
                .select(Projections.fields(ReviewArchiveResponse.class,
                                review.id,
                                review.content,
                                review.imageUrl,
                                review.rate,
                                review.likeCount,
                                review.updatedAt,
                                Projections.fields(ReviewArchiveResponse.BeerResponse.class,
                                        beer.id,
                                        beerInternational.name,
                                        beer.imageUrl
                                        ).as("beer")
                        )
                )
                .from(review)
                .innerJoin(review.member, member)
                .innerJoin(review.beer, beer)
                .innerJoin(beer.beerInternationals, beerInternational)
                .where(
                        matchLanguage(language),
                        matchOauthId(oauthId)
                );

        long totalElements = query.fetch().size();

        List<ReviewArchiveResponse> reviewResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy(QReview.class, review.getMetadata().getName()))
                .fetch();

        return new PageCustomImpl<>(reviewResponseList, pageRequest, totalElements);
    }

    private BooleanExpression matchLanguage(LanguageType language) {

        return beerInternational.id.language.stringValue().eq(language.toString());
    }

    private BooleanExpression matchOauthId(String oauthId) {
        return member.oauthId.eq(oauthId);
    }
}
