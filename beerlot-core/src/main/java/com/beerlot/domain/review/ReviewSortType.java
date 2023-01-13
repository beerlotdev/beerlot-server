package com.beerlot.domain.review;

import com.beerlot.domain.common.entity.SortType;
import com.beerlot.domain.common.util.SortTypeHelper;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@AllArgsConstructor
public enum ReviewSortType implements SortType {
    RECENTLY_UPDATED("updated_at", Direction.DESC),
    MOST_LIKES("like_count", Direction.DESC),
    HIGH_RATE("rate", Direction.DESC),
    LOW_RATE("rate", Direction.ASC)
    ;

    private String property;
    private Direction direction;

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public <T> OrderSpecifier[] orderBy(Class clazz, EntityPathBase<T> path) {
        return SortTypeHelper.orderBy(clazz, path, this);
    }
}
