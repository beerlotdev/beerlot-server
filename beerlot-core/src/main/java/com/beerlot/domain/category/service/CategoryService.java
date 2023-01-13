package com.beerlot.domain.category.service;

import com.beerlot.domain.category.Category;
import com.beerlot.domain.category.CategoryInternational;
import com.beerlot.domain.category.CategoryInternationalId;
import com.beerlot.domain.category.repository.CategoryInternationalRepository;
import com.beerlot.domain.category.repository.CategoryRepository;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryInternationalRepository categoryInternationalRepository;

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY__NOT_EXIST.getMessage()));
    }

    @Transactional(readOnly = true)
    private CategoryInternational findCategoryInternationalByKey(Long id, LanguageType language) {
        return categoryInternationalRepository.findById(new CategoryInternationalId(id, language))
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_INTERNATIONAL__NOT_EXIST.getMessage()));
    }
}
