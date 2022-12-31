package com.beerlot.domain.category;

import com.beerlot.domain.beer.BeerInternational;
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
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryInternational> categoryInternationals = new ArrayList<>();

    @Builder
    public Category(Category parent) {
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
