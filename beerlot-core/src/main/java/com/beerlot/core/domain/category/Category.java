package com.beerlot.core.domain.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

    @Column(name = "name_ko", nullable = false, unique = true)
    private String nameKo;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @Builder
    public Category(String nameEn, String nameKo, String description, Category parent) {
        this.nameEn = nameEn;
        this.nameKo = nameKo;
        this.description = description == null ? "" : description;
        setParent(parent);
    }

    public void setParent(Category parent) {
        if (Objects.isNull(parent)) {
            return;
        }
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
