package com.beerlot.domain.beer;

import com.beerlot.domain.common.entity.SortType;
import com.beerlot.domain.common.util.SortTypeHelper;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort.Direction;

@AllArgsConstructor
@Getter
public enum BeerSortType implements SortType {
    
    MOST_LIKES("like_count", Direction.DESC),
    MOST_REVIEWS("review_count", Direction.DESC),
    HIGH_RATE("rate", Direction.DESC)
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
