package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.common.BaseEntity;
import com.beerlot.core.domain.review.Review;
import com.beerlot.core.domain.tag.BeerTag;
import com.beerlot.core.domain.tag.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "beer")
public class Beer extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

    @Column(name = "name_ko", nullable = false, unique = true)
    private String nameKo;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @Column(name = "volume", nullable = false)
    private Float volume;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "beer")
    private List<BeerTag> beerTags = new ArrayList<>();

    @OneToMany(mappedBy = "beer")
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "like_count", columnDefinition = "int default 0")
    private long likeCount = 0L;

    public List<Tag> getTags() {
        return this.beerTags.stream().map(BeerTag::getTag).collect(Collectors.toList());
    }

    public void likeBeer() {
        this.likeCount += 1;
    }

    public void unlikeBeer() {
        this.likeCount -= 1;
    }

    @Builder
    public Beer(Long id, String nameEn, String nameKo, String description, Float volume, Country country, Category category, String imageUrl) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameKo = nameKo;
        this.description = description;
        this.volume = volume;
        this.country = country;
        this.category = category;
        this.imageUrl = imageUrl;
    }
}

