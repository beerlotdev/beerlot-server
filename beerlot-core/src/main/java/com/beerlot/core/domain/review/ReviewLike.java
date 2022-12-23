package com.beerlot.core.domain.review;

import com.beerlot.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
