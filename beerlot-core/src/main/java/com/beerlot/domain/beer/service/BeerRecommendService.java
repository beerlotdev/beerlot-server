package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.beer.repository.BeerRecommendRepository;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerRecommendService {

    private final BeerService beerService;

    private final MemberService memberService;

    private final BeerRecommendRepository beerRecommendRepository;

    public List<BeerResponse> recommend(String oauthId, LanguageType languageType, int amount) {
        Member foundMember = memberService.findMemberByOauthId(oauthId);
        DataModel dataModel = beerRecommendRepository.getDataModel();

        List<BeerResponse> response = new ArrayList<>();
        try {
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
            GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

            List<RecommendedItem> recommend = itemBasedRecommender.recommend(foundMember.getId(), amount);

            for (RecommendedItem item : recommend) {
                response.add(beerService.findBeerByIdAndLanguage(item.getItemID(), languageType));
            }
        } catch (Exception e) {
            log.error("Beer Recommend Error : {}", e.getMessage());
        }

        return response;
    }
}
