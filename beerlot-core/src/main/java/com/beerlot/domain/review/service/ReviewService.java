package com.beerlot.domain.review.service;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.repository.BeerRepository;
import com.beerlot.domain.beer.service.BeerService;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.common.util.SortTypeHelper;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import com.beerlot.domain.review.dto.response.ReviewPage;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.beerlot.domain.review.repository.ReviewRepository;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


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
    public ReviewPage findByBeerId(Long beerId, Integer page, Integer size, ReviewSortType sort) {
        Beer beer = beerService.findBeerById(beerId);
        PageCustomRequest pageRequest = new PageCustomRequest(page, size, sort);
        Page<Review> reviewPage = reviewRepository.findByBeer_Id(beerId, (Pageable) PageRequest.of(page-1, size, SortTypeHelper.sortBy(true, sort)));
        List<ReviewResponse> reviewResponseList = reviewPage.getContent().stream().map(ReviewResponse::of).collect(Collectors.toList());
        return new ReviewPage(reviewResponseList, pageRequest, reviewPage.getTotalElements());
    }

    public void createReview(String oauthId, Long beerId, ReviewRequest reviewRequest) {
        Beer beer = beerService.findBeerById(beerId);
        Member member = memberService.findMemberByOauthId(oauthId);

        Review review = ReviewRequest.to(reviewRequest, beer, member);

        beer.addReview();
        beer.calculateRate(review.getRate());

        reviewRepository.save(review);
    }

    public ReviewResponse updateReview(String oauthId, Long reviewId, ReviewRequest reviewRequest) {
        Review review = findById(reviewId);

        isReviewOwner(oauthId, review);

        review.update(reviewRequest);
        review.getBeer().calculateRate(review.getRate());

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
    public ReviewPage findAllReviews(Integer page, Integer size, ReviewSortType sort) {
        PageCustomRequest pageRequest = new PageCustomRequest(page, size, sort);
        Page<Review> reviewPage = reviewRepository.findAll((Pageable) PageRequest.of(page-1, size, SortTypeHelper.sortBy(true, sort)));
        List<ReviewResponse> reviewResponseList = reviewPage.getContent().stream().map(ReviewResponse::of).collect(Collectors.toList());
        return new ReviewPage(reviewResponseList, pageRequest, reviewPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.REVIEW__NOT_FOUND.getMessage()));
    }

    @Transactional(readOnly = true)
    public void isReviewOwner(String oauthId, Review review) {
        if (!review.getMember().getOauthId().equals(oauthId)) {
            throw new AccessDeniedException(ErrorMessage.MEMBER__ACCESS_DENIED.getMessage());
        }
    }
}
