package com.beerlot.core.domain.tag;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerTagId implements Serializable {

    @Column(name = "beer_id")
    private Long beerId;

    @Column(name = "tag_id")
    private Long tagId;

    BeerTagId(Long beerId, Long tagId) {
        this.beerId = beerId;
        this.tagId = tagId;
    }
}

