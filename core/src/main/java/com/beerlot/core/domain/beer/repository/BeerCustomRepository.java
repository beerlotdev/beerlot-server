package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.tag.Tag;
import java.util.List;

public interface BeerCustomRepository {
    List<BeerCustomRepositoryImpl.BeerResDto> findByKeywordAndTags(String keyword, List<Tag> tags);
}
