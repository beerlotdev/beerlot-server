package com.beerlot.core.domain.review.repository;

import com.beerlot.core.domain.review.Review;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    Page<Review> findByBeer_Id(Long id, Pageable pageRequest);
}
