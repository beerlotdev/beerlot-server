package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.BeerLike;
import com.beerlot.core.domain.beer.BeerLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerLikeRepository extends JpaRepository<BeerLike, BeerLikeId> {
    boolean existsByBeer_IdAndMember_Id(Long beerId, Long memberId);
    void deleteByBeer_IdAndMember_Id(Long beerId, Long memberId);
}
