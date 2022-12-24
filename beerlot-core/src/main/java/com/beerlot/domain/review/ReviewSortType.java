package com.beerlot.domain.review;

import com.beerlot.domain.common.entity.SortType;
import com.beerlot.domain.common.util.SortTypeHelper;
import com.querydsl.core.types.OrderSpecifier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public enum ReviewSortType implements SortType {
    RECENTLY_UPDATED("updated_at", Sort.Direction.DESC),
    MOST_LIKES("like_count", Sort.Direction.DESC),
    HIGH_RATE("rate", Sort.Direction.DESC),
    LOW_RATE("rate", Sort.Direction.ASC)
    ;

    private final String property;
    private final Sort.Direction direction;

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Sort.Direction getDirection() {
        return this.direction;
    }

    @Override
    public OrderSpecifier[] orderBy(Class clazz) {
        return SortTypeHelper.orderBy(clazz, this);
    }
}
