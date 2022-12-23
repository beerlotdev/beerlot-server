package com.beerlot.core.domain.beer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeerInternationalId is a Querydsl query type for BeerInternationalId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBeerInternationalId extends BeanPath<BeerInternationalId> {

    private static final long serialVersionUID = 391816880L;

    public static final QBeerInternationalId beerInternationalId = new QBeerInternationalId("beerInternationalId");

    public final NumberPath<Long> beerId = createNumber("beerId", Long.class);

    public final EnumPath<com.beerlot.core.domain.common.entity.LanguageType> language = createEnum("language", com.beerlot.core.domain.common.entity.LanguageType.class);

    public QBeerInternationalId(String variable) {
        super(BeerInternationalId.class, forVariable(variable));
    }

    public QBeerInternationalId(Path<? extends BeerInternationalId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeerInternationalId(PathMetadata metadata) {
        super(BeerInternationalId.class, metadata);
    }

}

