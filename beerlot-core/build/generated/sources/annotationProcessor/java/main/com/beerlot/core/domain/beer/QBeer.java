package com.beerlot.core.domain.beer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBeer is a Querydsl query type for Beer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeer extends EntityPathBase<Beer> {

    private static final long serialVersionUID = -877182887L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBeer beer = new QBeer("beer");

    public final com.beerlot.core.domain.common.entity.QBaseEntity _super = new com.beerlot.core.domain.common.entity.QBaseEntity(this);

    public final ListPath<BeerInternational, QBeerInternational> beerInternationals = this.<BeerInternational, QBeerInternational>createList("beerInternationals", BeerInternational.class, QBeerInternational.class, PathInits.DIRECT2);

    public final com.beerlot.core.domain.category.QCategory category;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    public final NumberPath<Long> reviewCount = createNumber("reviewCount", Long.class);

    public final ListPath<com.beerlot.core.domain.review.Review, com.beerlot.core.domain.review.QReview> reviews = this.<com.beerlot.core.domain.review.Review, com.beerlot.core.domain.review.QReview>createList("reviews", com.beerlot.core.domain.review.Review.class, com.beerlot.core.domain.review.QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public final NumberPath<Float> volume = createNumber("volume", Float.class);

    public QBeer(String variable) {
        this(Beer.class, forVariable(variable), INITS);
    }

    public QBeer(Path<? extends Beer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBeer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBeer(PathMetadata metadata, PathInits inits) {
        this(Beer.class, metadata, inits);
    }

    public QBeer(Class<? extends Beer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.beerlot.core.domain.category.QCategory(forProperty("category"), inits.get("category")) : null;
    }

}

