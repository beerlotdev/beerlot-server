package com.beerlot.domain.beer;

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
@Table(name = "beer_like")
public class BeerLike {

    @EmbeddedId
    private BeerLikeId id;

    @MapsId("beerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected OffsetDateTime createdAt;

    public BeerLike(Beer beer, Member member) {
        setBeer(beer);
        setMember(member);
        this.id = new BeerLikeId(beer.getId(), member.getId());
    }

    private void setBeer(Beer beer) {
        this.beer = beer;
        beer.likeBeer();
    }

    private void setMember(Member member) {
        this.member = member;
    }
}
