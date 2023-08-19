package com.beerlot.domain.brewery;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BreweryInternational {

    @EmbeddedId
    private BreweryInternationalId breweryInternationalId;

    @MapsId("breweryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    @Column
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;



}
