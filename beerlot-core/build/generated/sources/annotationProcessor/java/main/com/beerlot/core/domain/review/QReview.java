package com.beerlot.core.domain.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1246226855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.beerlot.core.domain.common.entity.QBaseEntity _super = new com.beerlot.core.domain.common.entity.QBaseEntity(this);

    public final com.beerlot.core.domain.beer.QBeer beer;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final com.beerlot.core.domain.member.QMember member;

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beer = inits.isInitialized("beer") ? new com.beerlot.core.domain.beer.QBeer(forProperty("beer"), inits.get("beer")) : null;
        this.member = inits.isInitialized("member") ? new com.beerlot.core.domain.member.QMember(forProperty("member")) : null;
    }

}

