package com.beerlot.domain.category.repository;

import com.beerlot.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select m from Category m where m.parent is null")
    List<Category> getTopLevelCategories();

}
