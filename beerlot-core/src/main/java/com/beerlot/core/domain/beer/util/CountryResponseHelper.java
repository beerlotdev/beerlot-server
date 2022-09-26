package com.beerlot.core.domain.beer.util;

import com.beerlot.api.generated.model.CountryResponse;
import com.beerlot.core.domain.beer.Country;

public class CountryResponseHelper {

    public static CountryResponse of(Country country) {

        CountryResponse countryResponse = new CountryResponse();

        countryResponse.setCode(country.name());

        return countryResponse;
    }
}
