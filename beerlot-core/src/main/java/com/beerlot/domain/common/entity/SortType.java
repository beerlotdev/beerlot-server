package com.beerlot.domain.common.entity;

import com.querydsl.core.types.OrderSpecifier;
import org.springframework.data.domain.Sort.Direction;

public interface SortType {

    String getProperty();
    Direction getDirection();
    <T> OrderSpecifier[] orderBy(Class clazz, String name);
}
