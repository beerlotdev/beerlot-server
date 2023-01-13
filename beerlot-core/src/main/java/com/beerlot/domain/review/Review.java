package com.beerlot.domain.review;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.common.entity.BaseEntity;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "image_url")
    private String imageUrl;

    @Convert(converter = BuyFromConverter.class)
    @Column(name = "buy_from")
    private Set<String> buyFrom = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "like_count", columnDefinition = "int default 0")
    private long likeCount = 0L;

    public void likeReview() {
        this.likeCount += 1;
    }

    public void unlikeReview() {
        this.likeCount -= 1;
    }

    @Builder
    public Review(String content, float rate, String imageUrl, Set<String> buyFrom, Beer beer, Member member) {
        this.content = content;
        this.rate = rate;
        this.imageUrl = imageUrl;
        this.buyFrom = buyFrom;
        this.beer = beer;
        this.member = member;
    }

    public void update(ReviewRequest reviewRequest) {
        this.content = reviewRequest.getContent();
        this.rate = reviewRequest.getRate();
        this.imageUrl = reviewRequest.getImageUrl();
        this.buyFrom = reviewRequest.getBuyFrom();
    }
}
