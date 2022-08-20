package com.beerlot.core.beer;

import com.beerlot.core.beer.Beer;
import com.beerlot.core.common.BaseEntity;
import com.beerlot.core.tag.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "beer_tag")
public class BeerTag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public BeerTag(Beer beer, Tag tag) {
        this.beer = beer;
        this.tag = tag;
    }
}
