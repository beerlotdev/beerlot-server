package com.beerlot.domain.category.repository;

import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.category.CategoryInternationalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryInternationalRepository extends JpaRepository<CategoryInternational, CategoryInternationalId> {
}
