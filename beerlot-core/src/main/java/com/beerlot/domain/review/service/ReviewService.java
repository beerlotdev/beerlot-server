package com.beerlot.domain.review.service;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.repository.BeerRepository;
import com.beerlot.domain.beer.service.BeerService;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import com.beerlot.domain.review.dto.response.ReviewArchiveResponse;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.beerlot.domain.review.repository.ReviewRepository;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final MemberService memberService;
    private final BeerService beerService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public ReviewResponse findReviewById(Long reviewId) {
        return ReviewResponse.of(findById(reviewId));
    }

    @Transactional(readOnly = true)
    public PageCustom<ReviewResponse> findByBeerId(Long beerId, PageCustomRequest pageRequest) {
        Beer beer = beerService.findBeerById(beerId);

        PageCustom<ReviewResponse> reviewPage = reviewRepository.findByBeerId(beerId, pageRequest);

        return reviewPage;
    }

    public void createReview(String oauthId, Long beerId, ReviewRequest reviewRequest) {
        Beer beer = beerService.findBeerById(beerId);
        Member member = memberService.findMemberByOauthId(oauthId);

        Review review = ReviewRequest.to(reviewRequest, beer, member);

        beer.addReview();
        beer.calculateRate(review.getRate());
        beer.updateBuyFrom(review.getBuyFrom(), reviewRequest.getBuyFrom());

        reviewRepository.save(review);
    }

    public ReviewResponse updateReview(String oauthId, Long reviewId, ReviewRequest reviewRequest) {
        Review review = findById(reviewId);

        isReviewOwner(oauthId, review);

        review.update(reviewRequest);
        review.getBeer().calculateRate(review.getRate());
        review.getBeer().updateBuyFrom(review.getBuyFrom(), reviewRequest.getBuyFrom());

        return ReviewResponse.of(review);
    }

    public void deleteReview(String oauthId, Long reviewId) {
        Review review = findById(reviewId);

        isReviewOwner(oauthId, review);

        review.getBeer().removeReview();
        review.getBeer().calculateRate(0 - review.getRate());

        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public PageCustom<ReviewResponse> findAllReviews(PageCustomRequest pageRequest) {
        return reviewRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.REVIEW__NOT_FOUND.getMessage()));
    }

    @Transactional(readOnly = true)
    public PageCustom<ReviewArchiveResponse> getReviewsByMember(String oauthId,
                                                                PageCustomRequest pageRequest,
                                                                LanguageType language) {
        return reviewRepository.findByMember(oauthId, pageRequest, language);
    }

    @Transactional(readOnly = true)
    private void isReviewOwner(String oauthId, Review review) {
        if (!review.getMember().getOauthId().equals(oauthId)) {
            throw new AccessDeniedException(ErrorMessage.MEMBER__ACCESS_DENIED.getMessage());
        }
    }
}
