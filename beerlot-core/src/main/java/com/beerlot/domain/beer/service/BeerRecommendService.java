package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BeerRecommendService {

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

    private final DataSource dataSource;

    private final BeerService beerService;

    private final MemberService memberService;

    public List<BeerResponse> recommend(String oauthId, LanguageType languageType, int amount) {
        Member foundMember = memberService.findMemberByOauthId(oauthId);

        DataModel dataModel = new PostgreSQLJDBCDataModel(
                dataSource, "recommend", "member_id", "item_id", "value", null
        );

        List<BeerResponse> response = new ArrayList<>();
        try {
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
            GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

            List<RecommendedItem> recommend = itemBasedRecommender.recommend(foundMember.getId(), amount);

            for (RecommendedItem item : recommend) {
                response.add(beerService.findBeerByIdAndLanguage(item.getItemID(), languageType));
            }
        } catch (Exception e) {

        }

        return response;
    }
}
