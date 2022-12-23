package com.beerlot.core.domain.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewLikeId is a Querydsl query type for ReviewLikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReviewLikeId extends BeanPath<ReviewLikeId> {

    private static final long serialVersionUID = 1082411723L;

    public static final QReviewLikeId reviewLikeId = new QReviewLikeId("reviewLikeId");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public QReviewLikeId(String variable) {
        super(ReviewLikeId.class, forVariable(variable));
    }

    public QReviewLikeId(Path<? extends ReviewLikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewLikeId(PathMetadata metadata) {
        super(ReviewLikeId.class, metadata);
    }

}

