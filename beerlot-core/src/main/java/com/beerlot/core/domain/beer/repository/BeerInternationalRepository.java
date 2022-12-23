package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.BeerInternational;
import com.beerlot.core.domain.beer.BeerInternationalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerInternationalRepository extends JpaRepository<BeerInternational, BeerInternationalId> {
}
