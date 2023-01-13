package com.beerlot.domain.category;

import com.beerlot.domain.common.entity.LanguageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category_international")
public class CategoryInternational {

    @EmbeddedId
    private CategoryInternationalId id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder
    public CategoryInternational(Category category, LanguageType language, String name, String description) {
        this.id = new CategoryInternationalId(category.getId(), language);
        this.name = name;
        this.description = description;
        setCategory(category);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getCategoryInternationals().add(this);
    }
}
