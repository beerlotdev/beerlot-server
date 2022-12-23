package com.beerlot.core.domain.review.service;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.repository.BeerRepository;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import com.beerlot.core.domain.common.util.SortTypeHelper;
import com.beerlot.core.domain.member.repository.MemberRepository;
import com.beerlot.core.domain.review.Review;
import com.beerlot.core.domain.review.ReviewSortType;
import com.beerlot.core.domain.review.dto.request.ReviewRequest;
import com.beerlot.core.domain.review.dto.response.ReviewPage;
import com.beerlot.core.domain.review.dto.response.ReviewResponse;
import com.beerlot.core.domain.review.repository.ReviewRepository;
import com.beerlot.core.exception.ErrorMessage;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public ReviewResponse findById(Long reviewId) {
        checkReviewExist(reviewId);
        return ReviewResponse.of(reviewRepository.findById(reviewId).get());
    }

    @Transactional(readOnly = true)
    public ReviewPage findByBeerId(Long beerId, Integer page, Integer size, ReviewSortType sort) {
        checkBeerExist(beerId);
        PageCustomRequest pageRequest = new PageCustomRequest(page, size, sort);
        Page<Review> reviewPage = reviewRepository.findByBeer_Id(beerId, (Pageable) PageRequest.of(page-1, size, SortTypeHelper.sortBy(true, sort)));
        List<ReviewResponse> reviewResponseList = reviewPage.getContent().stream().map(ReviewResponse::of).collect(Collectors.toList());
        return new ReviewPage(reviewResponseList, pageRequest, reviewPage.getTotalElements());
    }

    public void createReview(Long beerId, ReviewRequest reviewRequest) {
        checkBeerExist(beerId);
        Beer beer = beerRepository.findById(beerId).get();
        Review review = Review.builder()
                .content(reviewRequest.getContent())
                .rate(reviewRequest.getRate())
                .imageUrl(reviewRequest.getImageUrl())
                .beer(beer)
                .member(memberRepository.findById(1L).get())
                .build();
        beer.addReview();
        beer.calculateRate(review.getRate());
        reviewRepository.save(review);
    }

    public ReviewResponse updateReview(Long reviewId, ReviewRequest reviewRequest) {
        checkReviewExist(reviewId);
        Review review = reviewRepository.findById(reviewId).get();
        review.updateModel(reviewRequest);
        review.getBeer().calculateRate(review.getRate());
        return ReviewResponse.of(review);
    }

    public void deleteReview(Long reviewId) {
        checkReviewExist(reviewId);
        Review review = reviewRepository.findById(reviewId).get();
        review.getBeer().removeReview();
        review.getBeer().calculateRate(0 - review.getRate());
        reviewRepository.delete(review);
    }

    public ReviewPage findAllReviews(Integer page, Integer size, ReviewSortType sort) {
        PageCustomRequest pageRequest = new PageCustomRequest(page, size, sort);
        Page<Review> reviewPage = reviewRepository.findAll((Pageable) PageRequest.of(page-1, size, SortTypeHelper.sortBy(true, sort)));
        List<ReviewResponse> reviewResponseList = reviewPage.getContent().stream().map(ReviewResponse::of).collect(Collectors.toList());
        return new ReviewPage(reviewResponseList, pageRequest, reviewPage.getTotalElements());
    }

    private void checkBeerExist(Long beerId) {
        if (!beerRepository.existsById(beerId)) {
            throw new NotFoundException(ErrorMessage.BEER__NOT_EXIST);
        }
    }

    private void checkReviewExist(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NotFoundException(ErrorMessage.REVIEW_NOT_FOUND);
        }
    }
}
