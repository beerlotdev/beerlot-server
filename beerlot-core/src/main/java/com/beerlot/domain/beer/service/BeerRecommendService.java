package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.dto.response.BeerRecommendResponse;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.beer.repository.BeerRecommendRepository;
import com.beerlot.domain.category.Category;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerRecommendService {

    private final MemberService memberService;
    private final BeerLikeService beerLikeService;
    private final BeerRecommendRepository beerRecommendRepository;

    public Map<Long, Long> recommend(String oauthId) {
        Member member = memberService.findMemberByOauthId(oauthId);
        List<Beer> likedBeers = beerLikeService.getLikedBeers(member.getId());

        MappedCategory aggregated = aggregate(likedBeers);
        MappedCategory excluded = exclude(aggregated);
        if (excluded.parents.size() == 0 && excluded.children.size() == 0) {
            return calculateRatio(new MappedCategory(null, aggregated.children), 1L);
        }
        else {
            long gcd = getGcd(excluded);
            return calculateRatio(excluded, gcd);
        }
    }

    public MappedCategory aggregate(List<Beer> beers) {
        Map<Category, Long> parents = new HashMap<>();
        Map<Category, Long> children = new HashMap<>();
        beers.stream().forEach(beer -> {
            parents.merge(beer.getCategory().getParent(), 1L, Long::sum);
            children.merge(beer.getCategory(), 1L, Long::sum);
        });

        return new MappedCategory(parents, children);
    }

    public MappedCategory exclude(MappedCategory mappedCategory) {
        HashMap<Category, Long> parents = new HashMap<>(mappedCategory.parents);
        HashMap<Category, Long> children = new HashMap<>(mappedCategory.children);

        parents.entrySet().removeIf(category -> category.getValue()==1L);
        children.entrySet().removeIf(category -> category.getValue()==1L);

        children.entrySet().forEach(child -> {
            if (Objects.equals(parents.get(child.getKey().getParent()), child.getValue())) {
                parents.remove(child.getKey().getParent());
            }
        });

        return new MappedCategory(parents, children);
    }

    public long getGcd(MappedCategory category) {
        List<Long> weights = new ArrayList<>();
        weights.addAll(category.parents.values());
        weights.addAll(category.children.values());

        if (weights.size() == 1) {
            return weights.get(0);
        }

        long gcd = gcd(weights.get(0), weights.get(1));
        for(int i= 2 ; i < weights.size() ; i++) {
            gcd = gcd(gcd, weights.get(i));
        }
        return gcd;
    }

    public Map<Long, Long> calculateRatio(MappedCategory category, long gcd) {
        Map<Long, Long> result = new HashMap<>();
        if (category.parents != null) {
            category.parents.entrySet().forEach(parent -> {
                result.put(parent.getKey().getId(), parent.getValue() / gcd);
            });
        }
        category.children.entrySet().forEach(child -> {
            result.put(child.getKey().getId(), child.getValue() / gcd);
        });

        return result;
    }

    private long gcd(long x, long y) {
        if (x%y == 0) {
            return y;
        }
        return gcd(y, x%y);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MappedCategory {
        Map<Category, Long> parents;
        Map<Category, Long> children;
    }

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
