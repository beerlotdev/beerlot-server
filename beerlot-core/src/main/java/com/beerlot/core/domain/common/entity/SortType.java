package com.beerlot.core.domain.common.entity;

import com.querydsl.core.types.OrderSpecifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface SortType {

    String getProperty();
    Direction getDirection();
    Sort sortBy(boolean isCamelCase);
    OrderSpecifier[] orderBy();
}
