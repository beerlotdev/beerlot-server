package com.beerlot.core.domain.beer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBeerLike is a Querydsl query type for BeerLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeerLike extends EntityPathBase<BeerLike> {

    private static final long serialVersionUID = -1558081648L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBeerLike beerLike = new QBeerLike("beerLike");

    public final QBeer beer;

    public final QBeerLikeId id;

    public final com.beerlot.core.domain.member.QMember member;

    public QBeerLike(String variable) {
        this(BeerLike.class, forVariable(variable), INITS);
    }

    public QBeerLike(Path<? extends BeerLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBeerLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBeerLike(PathMetadata metadata, PathInits inits) {
        this(BeerLike.class, metadata, inits);
    }

    public QBeerLike(Class<? extends BeerLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beer = inits.isInitialized("beer") ? new QBeer(forProperty("beer"), inits.get("beer")) : null;
        this.id = inits.isInitialized("id") ? new QBeerLikeId(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new com.beerlot.core.domain.member.QMember(forProperty("member")) : null;
    }

}

