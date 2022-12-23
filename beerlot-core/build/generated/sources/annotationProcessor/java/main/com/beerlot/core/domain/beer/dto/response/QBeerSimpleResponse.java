package com.beerlot.core.domain.beer.dto.response;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeerSimpleResponse is a Querydsl query type for BeerSimpleResponse
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBeerSimpleResponse extends BeanPath<BeerSimpleResponse> {

    private static final long serialVersionUID = 1630714392L;

    public static final QBeerSimpleResponse beerSimpleResponse = new QBeerSimpleResponse("beerSimpleResponse");

    public final StringPath category = createString("category");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final StringPath originCountry = createString("originCountry");

    public QBeerSimpleResponse(String variable) {
        super(BeerSimpleResponse.class, forVariable(variable));
    }

    public QBeerSimpleResponse(Path<? extends BeerSimpleResponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeerSimpleResponse(PathMetadata metadata) {
        super(BeerSimpleResponse.class, metadata);
    }

}

