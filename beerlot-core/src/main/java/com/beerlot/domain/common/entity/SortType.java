package com.beerlot.domain.common.entity;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.domain.Sort.Direction;

public interface SortType {

    String getProperty();
    Direction getDirection();
    <T> OrderSpecifier[] orderBy(Class clazz, String name);
}
