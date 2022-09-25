package com.beerlot.core.domain.review.controller;

import com.beerlot.api.generated.api.ReviewApi;
import com.beerlot.api.generated.model.ReviewCreateRequest;
import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.api.generated.model.ReviewUpdateRequest;
import com.beerlot.core.domain.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ReviewController implements ReviewApi {

    @Autowired
    private ReviewService reviewService;

    @Override
    public ResponseEntity<Void> createReview(Long beerId, ReviewCreateRequest reviewCreateRequest) {
        return ReviewApi.super.createReview(beerId, reviewCreateRequest);
    }

    @Override
    public ResponseEntity<Void> deleteReview(Long reviewId) {
        return ReviewApi.super.deleteReview(reviewId);
    }

    @Override
    public ResponseEntity<List<ReviewResponse>> findReviewsByBeerId(Long beerId) {
        return ReviewApi.super.findReviewsByBeerId(beerId);
    }

    @Override
    public ResponseEntity<ReviewResponse> findReviewById(Long reviewId) {
        return new ResponseEntity<>(reviewService.findById(reviewId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewResponse> updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest) {
        return ReviewApi.super.updateReview(reviewId, reviewUpdateRequest);
    }
}
