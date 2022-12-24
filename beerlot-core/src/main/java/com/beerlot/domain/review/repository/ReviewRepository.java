package com.beerlot.domain.review.repository;

import com.beerlot.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBeer_Id(Long id, Pageable pageable);
    Page<Review> findAll(Pageable pageable);
}
