package com.beerlot.domain.review.repository;

import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.QReview;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beerlot.domain.beer.QBeer.beer;
import static com.beerlot.domain.review.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public PageCustom<ReviewResponse> findByBeerId(Long beerId, PageCustomRequest pageRequest) {

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
                .innerJoin(review.beer, beer)
                .where(
                        beer.id.eq(beerId)
                );

        long totalElements = query.fetch().size();

        List<ReviewResponse> reviewResponseList = query
                .limit(pageRequest.getSize())
                .offset(pageRequest.getOffset())
                .orderBy(pageRequest.getSort().orderBy(QReview.class, review))
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
                .orderBy(pageRequest.getSort().orderBy(QReview.class, review))
                .fetch();

        return new PageCustomImpl<>(reviewResponseList, pageRequest, totalElements);
    }
}
