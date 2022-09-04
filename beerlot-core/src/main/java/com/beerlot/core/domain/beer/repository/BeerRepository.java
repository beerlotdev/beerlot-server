package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, BeerCustomRepository {
    Optional<Beer> findById(Long id);
}
