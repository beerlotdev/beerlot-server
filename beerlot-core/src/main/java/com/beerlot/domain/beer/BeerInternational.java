package com.beerlot.domain.beer;

import com.beerlot.domain.common.entity.LanguageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "beer_international")
public class BeerInternational {

    @EmbeddedId
    private BeerInternationalId id;

    @MapsId("beerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "origin_city")
    private String originCity;

    @Column(name = "origin_country")
    private String originCountry;

    @Builder
    public BeerInternational(Beer beer, LanguageType language, String name, String description, String originCountry, String originCity) {
        this.id = new BeerInternationalId(beer.getId(), language);
        this.name = name;
        this.description = description;
        this.originCountry = originCountry;
        this.originCity = originCity;
        setBeer(beer);
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
        beer.getBeerInternationals().add(this);
    }
}
