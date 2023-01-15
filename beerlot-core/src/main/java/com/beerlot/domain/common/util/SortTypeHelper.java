package com.beerlot.domain.common.util;

import com.beerlot.domain.common.entity.SortType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;


public class SortTypeHelper {

    public static Sort sortBy(SortType sortType) {
        return Sort.by(sortType.getDirection(), sortType.getProperty());
    }

    public static OrderSpecifier[] orderBy(Class clazz, String name, SortType sortType) {
        return SortTypeHelper.sortBy(sortType)
                .stream().filter(sortBy -> !sortBy.getProperty().isBlank())
                .map(sortBy -> {
                    PathBuilder pathBuilder = new PathBuilder<>(clazz, name).get(sortBy.getProperty());
                    Order _direction = sortBy.getDirection().isDescending() ? Order.DESC : Order.ASC;
                    return new OrderSpecifier(_direction, pathBuilder);
                }).toArray(OrderSpecifier[]::new);
    }
}
