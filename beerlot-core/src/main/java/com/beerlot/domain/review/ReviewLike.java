package com.beerlot.domain.review;

import com.beerlot.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review_like")
public class ReviewLike {

    @EmbeddedId
    private ReviewLikeId id;

    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected OffsetDateTime createdAt;

    public ReviewLike(Review review, Member member) {
        setReview(review);
        setMember(member);
        this.id = new ReviewLikeId(review.getId(), member.getId());
    }

    private void setReview(Review review) {
        this.review = review;
        review.likeReview();
    }

    private void setMember(Member member) {
        this.member = member;
    }
}
