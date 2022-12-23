package com.beerlot.core.domain.beer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBeerInternational is a Querydsl query type for BeerInternational
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeerInternational extends EntityPathBase<BeerInternational> {

    private static final long serialVersionUID = -656574795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBeerInternational beerInternational = new QBeerInternational("beerInternational");

    public final QBeer beer;

    public final StringPath description = createString("description");

    public final QBeerInternationalId id;

    public final StringPath name = createString("name");

    public final StringPath originCity = createString("originCity");

    public final StringPath originCountry = createString("originCountry");

    public QBeerInternational(String variable) {
        this(BeerInternational.class, forVariable(variable), INITS);
    }

    public QBeerInternational(Path<? extends BeerInternational> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBeerInternational(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBeerInternational(PathMetadata metadata, PathInits inits) {
        this(BeerInternational.class, metadata, inits);
    }

    public QBeerInternational(Class<? extends BeerInternational> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beer = inits.isInitialized("beer") ? new QBeer(forProperty("beer"), inits.get("beer")) : null;
        this.id = inits.isInitialized("id") ? new QBeerInternationalId(forProperty("id")) : null;
    }

}

