package com.beerlot.core.domain.common.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseResponse is a Querydsl query type for BaseResponse
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBaseResponse extends BeanPath<BaseResponse> {

    private static final long serialVersionUID = -586314385L;

    public static final QBaseResponse baseResponse = new QBaseResponse("baseResponse");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public QBaseResponse(String variable) {
        super(BaseResponse.class, forVariable(variable));
    }

    public QBaseResponse(Path<? extends BaseResponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseResponse(PathMetadata metadata) {
        super(BaseResponse.class, metadata);
    }

}

