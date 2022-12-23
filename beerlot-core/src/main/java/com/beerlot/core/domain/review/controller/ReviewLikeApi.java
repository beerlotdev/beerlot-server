package com.beerlot.core.domain.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/reviews")
public interface ReviewLikeApi {
    @PostMapping("/{reviewId}/likes")
    ResponseEntity<Void> createReviewLike(@PathVariable("reviewId") Long reviewId);

    @DeleteMapping("/{reviewId}/likes")
    ResponseEntity<Void> deleteReviewLike(@PathVariable("reviewId") Long reviewId);
}

