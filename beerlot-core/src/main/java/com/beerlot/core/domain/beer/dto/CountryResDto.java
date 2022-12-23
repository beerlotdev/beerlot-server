package com.beerlot.core.domain.beer.dto;

import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.common.dto.BaseResDto;

public class CountryResDto extends BaseResDto {

    private String code;

    private String nameEn;

    private String nameKo;

    public CountryResDto(String code, String nameEn, String nameKo) {
        this.code = code;
        this.nameEn = nameEn;
        this.nameKo = nameKo;
    }

    public static CountryResDto of(Country country) {
        return new CountryResDto(country.name(), country.getNameEn(), country.getNameKo());
    }
}
