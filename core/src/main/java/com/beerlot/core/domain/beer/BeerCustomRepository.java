package com.beerlot.core.domain.beer;

import com.beerlot.core.domain.tag.Tag;
import java.util.List;

public interface BeerCustomRepository {
    List<Beer> findByKeywordAndTags(String keyword, List<Tag> tags);
}
