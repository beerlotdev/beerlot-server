package com.beerlot.core.domain.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewLike is a Querydsl query type for ReviewLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewLike extends EntityPathBase<ReviewLike> {

    private static final long serialVersionUID = 1422353808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewLike reviewLike = new QReviewLike("reviewLike");

    public final QReviewLikeId id;

    public final com.beerlot.core.domain.member.QMember member;

    public final QReview review;

    public QReviewLike(String variable) {
        this(ReviewLike.class, forVariable(variable), INITS);
    }

    public QReviewLike(Path<? extends ReviewLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewLike(PathMetadata metadata, PathInits inits) {
        this(ReviewLike.class, metadata, inits);
    }

    public QReviewLike(Class<? extends ReviewLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QReviewLikeId(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new com.beerlot.core.domain.member.QMember(forProperty("member")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

