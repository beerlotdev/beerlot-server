package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.domain.beer.dto.BeerResDto;
import com.beerlot.core.domain.tag.Tag;

import java.util.List;

public interface BeerCustomRepository {
    List<BeerResDto> findByKeywordAndTags(String keyword, List<Tag> tags);
}
