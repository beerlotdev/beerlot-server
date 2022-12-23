package com.beerlot.core.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 321538073L;

    public static final QMember member = new QMember("member1");

    public final com.beerlot.core.domain.common.entity.QBaseEntity _super = new com.beerlot.core.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath oauthId = createString("oauthId");

    public final EnumPath<com.beerlot.core.domain.auth.security.oauth.entity.ProviderType> provider = createEnum("provider", com.beerlot.core.domain.auth.security.oauth.entity.ProviderType.class);

    public final SetPath<RoleType, EnumPath<RoleType>> roles = this.<RoleType, EnumPath<RoleType>>createSet("roles", RoleType.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath statusMessage = createString("statusMessage");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

