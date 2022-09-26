package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.BeerLike;
import com.beerlot.core.domain.beer.BeerLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerLikeRepository extends JpaRepository<BeerLike, BeerLikeId> {
    Optional<BeerLike> findByBeer_IdAndMember_Id(Long beerId, Long memberId);
}
