package com.beerlot.domain.beer;

import com.beerlot.domain.category.Category;
import com.beerlot.domain.common.entity.BaseEntity;
import com.beerlot.domain.review.BuyFromConverter;
import com.beerlot.domain.review.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "brewery", nullable = false)
    private String brewery;

    @Column(name = "calorie")
    private Integer calorie;

    @Column(name = "calorie_unit", nullable = false)
    private Integer calorieUnit;

    @Convert(converter = BuyFromConverter.class)
    @Column(name = "buy_from")
    private Set<String> buyFrom = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

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

    public void updateBuyFrom(Set<String> oldBuyFrom, Set<String> newBuyFrom) {
        Set<String> temp = new HashSet<>();

        for (String buyFrom : newBuyFrom) {
            if (!oldBuyFrom.contains(buyFrom)) {
                temp.add(buyFrom);
            }
        }
        this.buyFrom = temp;
    }

    @Builder
    public Beer(Long id, Float volume, Category category, String imageUrl, Set<String> buyFrom) {
        this.id = id;
        this.volume = volume;
        this.category = category;
        this.imageUrl = imageUrl;
        this.buyFrom = buyFrom;
    }
}

