package com.beerlot.core.domain.review.controller;

import com.beerlot.api.generated.api.ReviewApi;
import com.beerlot.api.generated.model.ReviewCreateRequest;
import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.api.generated.model.ReviewUpdateRequest;
import com.beerlot.core.exception.NotFoundException;
import com.beerlot.core.domain.review.service.ReviewService;
import com.beerlot.core.domain.review.util.page.ReviewPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ReviewController implements ReviewApi {

    @Autowired
    private ReviewService reviewService;

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
    public ResponseEntity<ReviewPage> findReviewsByBeerId(Long beerId, Integer page, Integer size) {
        try {
            return new ResponseEntity<>(reviewService.findByBeerId(beerId, page, size), HttpStatus.OK);
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
