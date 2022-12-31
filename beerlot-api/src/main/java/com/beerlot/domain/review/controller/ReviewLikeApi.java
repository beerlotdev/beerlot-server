package com.beerlot.domain.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface ReviewLikeApi {
    @PostMapping("/reviews/{reviewId}/likes")
    ResponseEntity<Void> createReviewLike(@PathVariable("reviewId") Long reviewId);

    @DeleteMapping("/reviews/{reviewId}/likes")
    ResponseEntity<Void> deleteReviewLike(@PathVariable("reviewId") Long reviewId);
}
