package com.beerlot.domain.beer.repository;

import lombok.RequiredArgsConstructor;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class BeerRecommendRepositoryImpl implements BeerRecommendRepository {

    /*
    create view recommend (member_id, item_id, value) as
    SELECT
        member_id,
        beer_id as item_id,
        '5.0' as value
    FROM
        beer_like
    UNION
    SELECT
        member_id,
        beer_id,
        rate as value
    FROM
        review;
     */

    private static final Long UPDATE_PERIOD = 5L;

    private LocalDateTime updatedAt;

    private DataModel dataModel;

    private final DataSource dataSource;

    @Override
    public synchronized DataModel getDataModel() {
        if (updatedAt.isBefore(LocalDateTime.now().minusMinutes(UPDATE_PERIOD))) {
            updatedAt = LocalDateTime.now();

            dataModel = new PostgreSQLJDBCDataModel(
                    dataSource,
                    "recommend",
                    "member_id",
                    "item_id",
                    "value",
                    null
            );
        }

        return dataModel;
    }
}
