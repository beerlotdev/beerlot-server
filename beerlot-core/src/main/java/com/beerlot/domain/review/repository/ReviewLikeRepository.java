package com.beerlot.domain.review.repository;

import com.beerlot.domain.review.ReviewLike;
import com.beerlot.domain.review.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {
    boolean existsByReview_IdAndMember_Id(Long reviewId, Long memberId);
    void deleteByReview_IdAndMember_Id(Long reviewId, Long memberId);
    List<ReviewLike> findByMember_Id(Long memberId);
}
