package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.dto.response.BeerRecommendResponse;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.beer.repository.BeerRecommendRepository;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerRecommendService {

    private final MemberService memberService;

    private final BeerRecommendRepository beerRecommendRepository;

    public BeerRecommendResponse recommend(String oauthId, int amount) {
        Member foundMember = memberService.findMemberByOauthId(oauthId);
        DataModel dataModel = beerRecommendRepository.getDataModel();

        try {
            ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
            GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

            List<Long> recommendItems = itemBasedRecommender.recommend(foundMember.getId(), amount)
                    .stream()
                    .map(RecommendedItem::getItemID)
                    .toList();

            return new BeerRecommendResponse(recommendItems);
        } catch (Exception e) {
            log.error("Beer Recommend Error : {}", e.getMessage());
            e.printStackTrace();
        }

        return new BeerRecommendResponse(null);
    }
}
