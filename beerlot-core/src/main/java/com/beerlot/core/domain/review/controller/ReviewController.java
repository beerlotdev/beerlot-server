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
        try {
            reviewLikeService.likeReview(reviewId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<Void> deleteReviewLike(Long reviewId) {
        try {
            reviewLikeService.unlikeReview(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<Void> createReview(Long beerId, ReviewCreateRequest reviewCreateRequest) {
        try {
            reviewService.createReview(beerId, reviewCreateRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<Void> deleteReview(Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<ReviewPage> findReviewsByBeerId(Long beerId, Integer page, Integer size, ReviewSortType sort) {
        try {
            return new ResponseEntity<>(reviewService.findByBeerId(beerId, page, size, sort), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<ReviewResponse> findReviewById(Long reviewId) {
        try {
            return new ResponseEntity<>(reviewService.findById(reviewId), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }

    @Override
    public ResponseEntity<ReviewResponse> updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest) {
        try {
            return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewUpdateRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }
}
