package com.beerlot.core.domain.tag;

import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "beer_tag")
@Getter
public class BeerTag extends BaseEntity {

    @EmbeddedId
    private BeerTagId id;

    @MapsId("beerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @MapsId("tagId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public BeerTag(Beer beer, Tag tag) {
        this.beer = beer;
        this.tag = tag;
        this.id = new BeerTagId(beer.getId(), tag.getId());
    }
}

