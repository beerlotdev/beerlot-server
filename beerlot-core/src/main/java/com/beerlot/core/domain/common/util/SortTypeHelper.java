package com.beerlot.core.domain.common.util;

import com.beerlot.core.domain.beer.QBeer;
import com.beerlot.core.domain.common.entity.SortType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
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

    public static OrderSpecifier[] orderBy(Class clazz, SortType sortType) {
        return SortTypeHelper.sortBy(false, sortType)
                .stream().filter(sortBy -> !sortBy.getProperty().isBlank())
                .map(sortBy -> {
                    Order _direction = sortBy.getDirection().isDescending() ? Order.DESC : Order.ASC;
                    Path<QBeer> postPath = Expressions.path(clazz, sortBy.getProperty());
                    return new OrderSpecifier(_direction, postPath);
                }).toArray(OrderSpecifier[]::new);
    }
}
