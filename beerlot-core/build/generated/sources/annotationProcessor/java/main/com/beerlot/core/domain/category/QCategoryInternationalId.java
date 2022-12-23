package com.beerlot.core.domain.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryInternationalId is a Querydsl query type for CategoryInternationalId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCategoryInternationalId extends BeanPath<CategoryInternationalId> {

    private static final long serialVersionUID = 242245872L;

    public static final QCategoryInternationalId categoryInternationalId = new QCategoryInternationalId("categoryInternationalId");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final EnumPath<com.beerlot.core.domain.common.entity.LanguageType> language = createEnum("language", com.beerlot.core.domain.common.entity.LanguageType.class);

    public QCategoryInternationalId(String variable) {
        super(CategoryInternationalId.class, forVariable(variable));
    }

    public QCategoryInternationalId(Path<? extends CategoryInternationalId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryInternationalId(PathMetadata metadata) {
        super(CategoryInternationalId.class, metadata);
    }

}

