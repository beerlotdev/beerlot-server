package com.beerlot.core.domain.review.service;

import com.beerlot.core.domain.beer.BeerLike;
import com.beerlot.core.domain.member.repository.MemberRepository;
import com.beerlot.core.domain.review.ReviewLike;
import com.beerlot.core.domain.review.repository.ReviewLikeRepository;
import com.beerlot.core.domain.review.repository.ReviewRepository;
import com.beerlot.core.exception.ConflictException;
import com.beerlot.core.exception.ErrorCode;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewLikeService {

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* TODO: Include memberId and validateMember */
    public void likeReview(Long reviewId) {
        checkReviewExist(reviewId);
        checkReviewLikeExist(reviewId, 1L, true);
        ReviewLike reviewLike = new ReviewLike(reviewRepository.findById(reviewId).get(), memberRepository.findById(1L).get());
        reviewLikeRepository.save(reviewLike);
    }

    /* TODO: Include memberId and validateMember */
    public void unlikeReview(Long reviewId) {
        checkReviewExist(reviewId);
        checkReviewLikeExist(reviewId, 1L, false);
        reviewLikeRepository.deleteByReview_IdAndMember_Id(reviewId, 1L);
        reviewRepository.findById(reviewId).get().unlikeReview();
    }

    public void checkReviewExist(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NotFoundException(ErrorCode.REVIEW_NOT_FOUND);
        }
    }

    private void checkReviewLikeExist(Long reviewId, Long memberId, boolean isPositive) {
        if (isPositive && reviewLikeRepository.existsByReview_IdAndMember_Id(reviewId, memberId)) {
            throw new ConflictException(ErrorCode.REVIEW_LIKE_CONFLICT);
        } else if (!isPositive && !reviewLikeRepository.existsByReview_IdAndMember_Id(reviewId, memberId)) {
            throw new NotFoundException(ErrorCode.REVIEW_LIKE_NOT_FOUND);
        }
    }
}
