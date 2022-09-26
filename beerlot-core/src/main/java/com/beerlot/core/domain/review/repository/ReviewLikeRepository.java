package com.beerlot.core.domain.review.repository;

import com.beerlot.core.domain.review.ReviewLike;
import com.beerlot.core.domain.review.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {
    boolean existsByReview_IdAndMember_Id(Long reviewId, Long memberId);
    void deleteByReview_IdAndMember_Id(Long reviewId, Long memberId);
}
