package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.common.entity.BaseEntity;
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

    @OneToMany(mappedBy = "beer")
    private List<BeerInternational> beerInternationals = new ArrayList<>();

    @Column(name = "like_count", columnDefinition = "int default 0")
    private long likeCount = 0L;

    @Column(name = "review_count", columnDefinition = "int default 0")
    private long reviewCount = 0L;

    @Column(name = "rate", columnDefinition = "float default 0.0")
    private float rate = 0F;

    public List<Tag> getTags() {
        return this.beerTags.stream().map(BeerTag::getTag).collect(Collectors.toList());
    }

    public void likeBeer() {
        this.likeCount += 1;
    }

    public void unlikeBeer() {
        this.likeCount -= 1;
    }

    public void addReview() {
        this.reviewCount += 1;
    }

    public void removeReview() {
        this.reviewCount -= 1;
    }

    public void calculateRate(float rate) {
        this.rate = rate > 0 ? (this.rate * (reviewCount - 1) + rate) / reviewCount : (this.rate * (reviewCount + 1) + rate) / reviewCount;
    }

    @Builder
    public Beer(Long id, Float volume, Category category, String imageUrl) {
        this.id = id;
        this.volume = volume;
        this.category = category;
        this.imageUrl = imageUrl;
    }
}

