package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public BeerLike(Beer beer, Member member) {
        setBeer(beer);
        setMember(member);
        this.id = new BeerLikeId(beer.getId(), member.getId());
    }

    private void setBeer(Beer beer) {
        this.beer = beer;
    }

    private void setMember(Member member) {
        this.member = member;
    }
}
