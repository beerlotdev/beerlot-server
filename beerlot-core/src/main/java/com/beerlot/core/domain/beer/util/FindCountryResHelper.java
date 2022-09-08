package com.beerlot.core.domain.beer.util;


import com.beerlot.api.generated.model.FindCountryResDto;
import com.beerlot.core.domain.beer.Country;

public class FindCountryResHelper {

    public static FindCountryResDto of(Country country) {

        FindCountryResDto findCountryResDto = new FindCountryResDto();

        findCountryResDto.setCode(country.name());

        return findCountryResDto;
    }
}
