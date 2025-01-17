package com.beerlot.domain.member;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.beer.BeerSortType;
import com.beerlot.domain.beer.dto.response.BeerSimpleResponse;
import com.beerlot.domain.beer.service.BeerService;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.member.dto.request.MemberProfileRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.response.ReviewArchiveResponse;
import com.beerlot.domain.review.service.ReviewLikeService;
import com.beerlot.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberApi {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final BeerService beerService;

    @Override
    public ResponseEntity<MemberResponse> getMemberProfile(OAuthUserPrincipal userPrincipal) {
        return new ResponseEntity<>(
                memberService.getProfile(memberService.findMemberByOauthId(userPrincipal.getOauthId())),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MemberResponse> updateMemberProfile(OAuthUserPrincipal userPrincipal, MemberProfileRequest memberProfileRequest) {
        return new ResponseEntity<>(
                memberService.updateProfile(memberService.findMemberByOauthId(userPrincipal.getOauthId()), memberProfileRequest),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageCustom<ReviewArchiveResponse>> getAllReviews(OAuthUserPrincipal userPrincipal,
                                                                           Integer page,
                                                                           Integer size,
                                                                           ReviewSortType sort,
                                                                           LanguageType language) {
        return new ResponseEntity<>(
                reviewService.getReviewsByMember(userPrincipal.getOauthId(),
                                                 new PageCustomRequest(page, size, sort),
                                                 language),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageCustom<BeerSimpleResponse>> getAllBeers(OAuthUserPrincipal userPrincipal,
                                                                        Integer page,
                                                                        Integer size,
                                                                        BeerSortType sort,
                                                                        LanguageType language) {
        return new ResponseEntity<>(
                beerService.getBeersByMember(userPrincipal.getOauthId(),
                                             new PageCustomRequest(page, size, sort),
                                             language),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Long>> getAllLikedReviews(OAuthUserPrincipal userPrincipal) {
        List<Long> reviewIds = reviewLikeService.getReviewLikesByMember(userPrincipal.getOauthId());
        if (reviewIds.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviewIds, HttpStatus.OK);
    }
}
