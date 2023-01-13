package com.beerlot.domain.common.util;

import com.beerlot.domain.common.entity.SortType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Sort;

public class SortTypeHelper {
    public static String changeToCamelCase(String snakeCase) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : snakeCase.split("_")) {
            stringBuilder.append(s.substring(0, 1).toUpperCase());
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }

    public static Sort sortBy(boolean isCamelCase, SortType sortType) {
        String property = sortType.getProperty();
        Sort.Direction direction = sortType.getDirection();

        return isCamelCase == true ? Sort.by(direction, SortTypeHelper.changeToCamelCase(property)) : Sort.by(direction, property);
    }

    public static <T> OrderSpecifier[] orderBy(Class clazz, EntityPathBase<T> path, SortType sortType) {
        return SortTypeHelper.sortBy(false, sortType)
                .stream().filter(sortBy -> !sortBy.getProperty().isBlank())
                .map(sortBy -> {
                    Order _direction = sortBy.getDirection().isDescending() ? Order.DESC : Order.ASC;
                    return new OrderSpecifier(_direction, Expressions.path(clazz, path, sortBy.getProperty()));
                }).toArray(OrderSpecifier[]::new);
    }
}
