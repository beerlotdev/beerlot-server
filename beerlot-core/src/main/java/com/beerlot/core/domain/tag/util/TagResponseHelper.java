package com.beerlot.core.domain.tag.util;

import com.beerlot.api.generated.model.TagResponse;
import com.beerlot.core.domain.tag.Tag;

public class TagResponseHelper {

    public static TagResponse of(Tag tag) {

        TagResponse tagResponse = new TagResponse();

        tagResponse.setId(tag.getId());
        tagResponse.setNameEn(tag.getNameEn());
        tagResponse.setNameKo(tag.getNameKo());

        return tagResponse;
    }
}
