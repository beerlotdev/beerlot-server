package com.beerlot.domain.beer.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
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
    private static final int UPDATE_PERIOD_IN_MINUTE = 5;

    @Value("${recommend.table}")
    private String preferenceTable;

    @Value("${recommend.user_id_column}")
    private String userIdColumn;

    @Value("${recommend.item_id_column}")
    private String itemIdColumn;

    @Value("${recommend.pref_column}")
    private String preferenceColumn;

    private final DataSource dataSource;

    private LocalDateTime updatedAt;

    private DataModel dataModel;


    @Override
    public DataModel getDataModel() {
        if (updatedAt.isBefore(LocalDateTime.now().minusMinutes(UPDATE_PERIOD_IN_MINUTE))) {
            dataModel.refresh(null);
            updatedAt = LocalDateTime.now();
        }

        return dataModel;
    }

    @PostConstruct
    private void init() {
        try {
            dataModel = new ReloadFromJDBCDataModel(
                    new PostgreSQLJDBCDataModel(
                            dataSource,
                            preferenceTable,
                            userIdColumn,
                            itemIdColumn,
                            preferenceColumn,
                            null
                    )
            );

            updatedAt = LocalDateTime.now();
        } catch (TasteException e) {
            log.error("getDataModel Exception : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
