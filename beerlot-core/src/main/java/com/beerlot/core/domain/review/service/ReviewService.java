package com.beerlot.core.domain.review.service;

import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.core.common.exception.ErrorCode;
import com.beerlot.core.common.exception.NotFoundException;
import com.beerlot.core.domain.beer.repository.BeerRepository;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import com.beerlot.core.domain.review.Review;
import com.beerlot.core.domain.review.repository.ReviewRepository;
import com.beerlot.core.domain.review.util.ReviewResponseHelper;
import com.beerlot.core.domain.review.util.page.ReviewPage;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ReviewResponse findById(Long id) {
        return ReviewResponseHelper.of(reviewRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public ReviewPage findByBeerId(Long beerId, Integer page, Integer size) {
            validateBeer(beerId);
            PageCustomRequest pageRequest = new PageCustomRequest(page, size);

            org.springframework.data.domain.Page<Review> reviewPage = reviewRepository.findByBeer_Id(beerId, (Pageable) PageRequest.of(page-1, size));
            List<ReviewResponse> reviewResponseList = reviewPage.getContent().stream().map(ReviewResponseHelper::of).collect(Collectors.toList());
            return new ReviewPage(reviewResponseList, pageRequest, reviewPage.getTotalElements());
    }

    private void validateBeer(Long beerId) {
        if (!beerRepository.existsById(beerId)) {
            throw new NotFoundException(ErrorCode.BEER_NOT_FOUND);
        }
    }
}
