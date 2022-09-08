package com.beerlot.core.domain.tag.util;

import com.beerlot.api.generated.model.FindTagResDto;
import com.beerlot.core.domain.tag.Tag;

public class FindTagResHelper {

    public static FindTagResDto of(Tag tag) {

        FindTagResDto findTagResDto = new FindTagResDto();

        findTagResDto.setId(tag.getId());
        findTagResDto.setNameEn(tag.getNameEn());
        findTagResDto.setNameKo(tag.getNameKo());

        return findTagResDto;
    }
}
