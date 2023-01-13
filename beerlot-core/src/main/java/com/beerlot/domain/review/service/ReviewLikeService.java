package com.beerlot.domain.review.service;

import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.ReviewLike;
import com.beerlot.domain.review.repository.ReviewLikeRepository;
import com.beerlot.domain.review.repository.ReviewRepository;
import com.beerlot.exception.ConflictException;
import com.beerlot.exception.ErrorMessage;
import com.beerlot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikeService {

    private final MemberService memberService;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewService reviewService;

    public void likeReview(String oauthId, Long reviewId) {
        Review review = reviewService.findById(reviewId);
        Member member = memberService.findMemberByOauthId(oauthId);

        checkReviewLikeExist(reviewId, member.getId(), true);

        ReviewLike reviewLike = new ReviewLike(review, member);
        reviewLikeRepository.save(reviewLike);
    }

    public void unlikeReview(String oauthId, Long reviewId) {
        Review review = reviewService.findById(reviewId);
        Member member = memberService.findMemberByOauthId(oauthId);

        checkReviewLikeExist(reviewId, member.getId(), false);

        reviewLikeRepository.deleteByReview_IdAndMember_Id(reviewId, member.getId());
        review.unlikeReview();
    }

    @Transactional(readOnly = true)
    private void checkReviewExist(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NotFoundException(ErrorMessage.REVIEW__NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    private void checkReviewLikeExist(Long reviewId, Long memberId, boolean isPositive) {
        if (isPositive && reviewLikeRepository.existsByReview_IdAndMember_Id(reviewId, memberId)) {
            throw new ConflictException(ErrorMessage.REVIEW_LIKE__CONFLICT.getMessage());
        } else if (!isPositive && !reviewLikeRepository.existsByReview_IdAndMember_Id(reviewId, memberId)) {
            throw new NotFoundException(ErrorMessage.REVIEW_LIKE__NOT_FOUND);
        }
    }
}
