package com.beerlot.domain.beer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerLikeId implements Serializable {

    private Long beerId;
    private Long memberId;

    public BeerLikeId(Long beerId, Long memberId) {
        this.beerId = beerId;
        this.memberId = memberId;
    }
}
