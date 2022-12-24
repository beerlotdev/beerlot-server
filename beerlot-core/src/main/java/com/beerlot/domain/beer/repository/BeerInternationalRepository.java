package com.beerlot.domain.beer.repository;

import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.beer.BeerInternationalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerInternationalRepository extends JpaRepository<BeerInternational, BeerInternationalId> {
}
