package com.beerlot.core.domain.beer.dto;

import com.beerlot.core.common.BaseResDto;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.category.dto.CategoryResDto;
import com.beerlot.core.domain.tag.BeerTag;
import com.beerlot.core.domain.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BeerResDto extends BaseResDto {

    private String nameEn;

    private String nameKo;

    private String description;

    private Country origin;

    private Float volume;

    private String imageUrl;

    private Category category;

    private List<Tag> tags;

    public static BeerResDto of(
            Beer beer
    ) {
        BeerResDto beerResDto = new BeerResDto();
        beerResDto.id = beer.getId();
        beerResDto.nameEn = beer.getNameEn();
        beerResDto.nameKo = beer.getNameKo();
        beerResDto.description = beer.getDescription();
        beerResDto.origin = beer.getOrigin();
        beerResDto.volume = beer.getVolume();
        beerResDto.imageUrl = beer.getImageUrl();
        beerResDto.category = beer.getCategory();
        beerResDto.tags = beer.getTags();

        return beerResDto;
    }
}
