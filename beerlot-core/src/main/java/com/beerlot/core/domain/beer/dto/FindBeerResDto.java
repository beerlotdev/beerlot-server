package com.beerlot.core.domain.beer.dto;

import com.beerlot.core.common.BaseResDto;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.tag.Tag;
import lombok.Getter;

import java.util.List;

@Getter
public class FindBeerResDto extends BaseResDto {

    private String nameEn;

    private String nameKo;

    private String description;

    private Country origin;

    private Float volume;

    private String imageUrl;

    private Category category;

    private List<Tag> tags;

    public static FindBeerResDto of(
            Beer beer
    ) {
        FindBeerResDto findBeerResDto = new FindBeerResDto();
        findBeerResDto.id = beer.getId();
        findBeerResDto.nameEn = beer.getNameEn();
        findBeerResDto.nameKo = beer.getNameKo();
        findBeerResDto.description = beer.getDescription();
        findBeerResDto.origin = beer.getOrigin();
        findBeerResDto.volume = beer.getVolume();
        findBeerResDto.imageUrl = beer.getImageUrl();
        findBeerResDto.category = beer.getCategory();
        findBeerResDto.tags = beer.getTags();

        return findBeerResDto;
    }
}
