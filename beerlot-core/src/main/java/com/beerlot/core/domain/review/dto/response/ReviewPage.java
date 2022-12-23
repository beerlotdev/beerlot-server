package com.beerlot.core.domain.review.dto.response;

import com.beerlot.core.domain.common.page.PageCustomCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;

import java.util.List;

public class ReviewPage extends PageCustomCustomImpl<ReviewResponse> {
    public ReviewPage(List<ReviewResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
