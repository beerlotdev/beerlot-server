package com.beerlot.core.domain.beer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeerLikeId is a Querydsl query type for BeerLikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBeerLikeId extends BeanPath<BeerLikeId> {

    private static final long serialVersionUID = 1627124939L;

    public static final QBeerLikeId beerLikeId = new QBeerLikeId("beerLikeId");

    public final NumberPath<Long> beerId = createNumber("beerId", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QBeerLikeId(String variable) {
        super(BeerLikeId.class, forVariable(variable));
    }

    public QBeerLikeId(Path<? extends BeerLikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeerLikeId(PathMetadata metadata) {
        super(BeerLikeId.class, metadata);
    }

}

