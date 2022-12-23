package com.beerlot.core.domain.review.util.sort;

import com.beerlot.core.domain.common.entity.SortType;
import com.beerlot.core.domain.review.QReview;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum ReviewSortType implements SortType {

    RECENTLY_UPDATED("updated_at", Sort.Direction.DESC),
    MOST_LIKES("like_count", Sort.Direction.DESC),
    HIGH_RATE("rate", Sort.Direction.DESC),
    LOW_RATE("rate", Sort.Direction.ASC)
    ;

    private String property;
    private Sort.Direction direction;

    @Override
    public Sort sortBy(boolean isCamelCase) {
        return isCamelCase == true ? Sort.by(direction, changeToCamelCase(property)) : Sort.by(direction, property);
    }

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Sort.Direction getDirection() {
        return this.direction;
    }

    public OrderSpecifier[] orderBy() {
        return sortBy(false).stream().filter(sortBy -> !sortBy.getProperty().isBlank())
                .map(sortBy -> {
                    Order direction = sortBy.getDirection().isDescending() ? Order.DESC : Order.ASC;
                    Path<QReview> postPath = Expressions.path(QReview.class, sortBy.getProperty());
                    return new OrderSpecifier(direction, postPath);
                }).toArray(OrderSpecifier[]::new);
    }

    private String changeToCamelCase(String snakeCase) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : snakeCase.split("_")) {
            stringBuilder.append(s.substring(0, 1).toUpperCase());
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
