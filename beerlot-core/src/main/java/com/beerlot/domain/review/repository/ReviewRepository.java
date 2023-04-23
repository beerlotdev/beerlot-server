package com.beerlot.domain.review.repository;

import com.beerlot.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
    Optional<Review> findByBeer_IdAndMember_Id(Long beerId, Long memberId);
    boolean existsByIdNotAndBuyFrom(Long reviewId, String buyFrom);
}
