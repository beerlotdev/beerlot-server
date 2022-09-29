package com.beerlot.core.domain.common;

import com.querydsl.core.types.OrderSpecifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface SortType {

    String getProperty();
    Direction getDirection();
    Sort sortBy();
    OrderSpecifier[] orderBy();
}
