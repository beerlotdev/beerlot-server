package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, BeerCustomRepository {
    Optional<Beer> findById(Long id);
    List<Beer> findTop10ByOrderByLikeCountDesc();
}
