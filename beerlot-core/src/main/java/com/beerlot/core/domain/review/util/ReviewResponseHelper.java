package com.beerlot.core.domain.review.util;

import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.core.domain.review.Review;

public class ReviewResponseHelper {

    public static ReviewResponse of(Review review) {

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setId(review.getId());
        reviewResponse.setContent(review.getContent());
        reviewResponse.setRate(review.getRate());
        reviewResponse.setCreatedAt(review.getCreatedAt());
        reviewResponse.setUpdatedAt(review.getUpdatedAt());
        reviewResponse.setLikeCount(review.getLikeCount());

        return reviewResponse;
    }
}
