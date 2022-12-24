package com.beerlot.domain.review.controller;

import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import com.beerlot.domain.review.dto.response.ReviewPage;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
public interface ReviewApi {

    @PostMapping("/beers/{beerId}/reviews")
    ResponseEntity<Void> createReview(@PathVariable("beerId") Long beerId,
                                      @RequestBody ReviewRequest reviewRequest);

    @GetMapping("/beers/{beerId}/reviews")
    ResponseEntity<ReviewPage> findReviewsByBeerId(@PathVariable("beerId") Long beerId,
                                                   @RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "size") Integer size,
                                                   @RequestParam(value = "sort") ReviewSortType sort);

    @GetMapping("/reviews")
    ResponseEntity<ReviewPage> findAllReviews(@RequestParam(value = "page", required = true) Integer page,
                                              @RequestParam(value = "size", required = true) Integer size,
                                              @RequestParam(value = "sort", required = true) ReviewSortType sort);

    @GetMapping("/reviews/{reviewId}")
    ResponseEntity<ReviewResponse> findReviewById(@PathVariable("reviewId") Long reviewId);

    @DeleteMapping("/reviews/{reviewId}")
    ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId);

    @PatchMapping("/reviews/{reviewId}")
    ResponseEntity<ReviewResponse> updateReview(@PathVariable("reviewId") Long reviewId,
                                                @RequestBody ReviewRequest reviewRequest);

}
