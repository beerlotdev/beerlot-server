package com.beerlot.core.domain.review;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.common.BaseEntity;
import com.beerlot.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Review(String content, float rate, String imageUrl, Beer beer, Member member) {
        this.content = content;
        this.rate = rate;
        this.imageUrl = imageUrl;
        this.beer = beer;
        this.member = member;
    }
}
