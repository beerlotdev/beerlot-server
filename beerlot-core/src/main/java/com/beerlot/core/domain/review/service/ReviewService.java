package com.beerlot.core.domain.review.service;

import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.core.domain.review.repository.ReviewRepository;
import com.beerlot.core.domain.review.util.ReviewResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewResponse findById(Long id) {
        return ReviewResponseHelper.of(reviewRepository.findById(id).get());
    }
}
