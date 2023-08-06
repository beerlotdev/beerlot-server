package com.beerlot.domain.category;

import com.beerlot.domain.glassware.Glassware;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.*;

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

    @BatchSize(size = 50)
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @BatchSize(size = 50)
    @OneToMany(mappedBy = "category")
    private List<CategoryInternational> categoryInternationals = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "category_glassware",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "glassware_id")
    )
    private Set<Glassware> glasswares = new HashSet<>();

    public void addGlassware(Glassware glassware) {
        glasswares.add(glassware);
        glassware.getCategories().add(this);
    }

    public void removeGlassware(Glassware glassware) {
        glasswares.remove(glassware);
        glassware.getCategories().remove(this);
    }

    @Builder
    public Category(Long id, Category parent) {
        this.id = id;
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
