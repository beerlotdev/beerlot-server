package com.beerlot.core.domain.review.controller;

import com.beerlot.api.generated.api.ReviewApi;
import com.beerlot.api.generated.api.ReviewLikeApi;
import com.beerlot.api.generated.model.ReviewCreateRequest;
import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.api.generated.model.ReviewUpdateRequest;
import com.beerlot.core.domain.review.service.ReviewLikeService;
import com.beerlot.core.domain.review.service.ReviewService;
import com.beerlot.core.domain.review.util.page.ReviewPage;
import com.beerlot.core.domain.review.util.sort.ReviewSortType;
import com.beerlot.core.exception.ConflictException;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ReviewController implements ReviewApi, ReviewLikeApi {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewLikeService reviewLikeService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ReviewApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> createReviewLike(Long reviewId) {
        reviewLikeService.likeReview(reviewId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteReviewLike(Long reviewId) {
        reviewLikeService.unlikeReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Void> createReview(Long beerId, ReviewCreateRequest reviewCreateRequest) {
        reviewService.createReview(beerId, reviewCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteReview(Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ReviewPage> findReviewsByBeerId(Long beerId, Integer page, Integer size, ReviewSortType sort) {
        return new ResponseEntity<>(reviewService.findByBeerId(beerId, page, size, sort), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewResponse> findReviewById(Long reviewId) {
        return new ResponseEntity<>(reviewService.findById(reviewId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewResponse> updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewUpdateRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewPage> findAllReviews(Integer page, Integer size, ReviewSortType sort) {
        try {
            return new ResponseEntity<>(reviewService.findAllReviews(page, size, sort), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
