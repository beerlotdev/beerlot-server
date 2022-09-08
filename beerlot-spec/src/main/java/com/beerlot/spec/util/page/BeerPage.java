package com.beerlot.spec.util.page;

import com.beerlot.api.generated.model.FindBeerResDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPage extends PageImpl<FindBeerResDto> {
    public BeerPage(List<FindBeerResDto> contents, Pageable pageable, long total) {
        super(contents, pageable, total);
    }
}
