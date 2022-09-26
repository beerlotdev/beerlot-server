package com.beerlot.core.domain.review.util.page;

import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.core.domain.common.page.PageCustomCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewPage extends PageCustomCustomImpl<ReviewResponse> {
    public ReviewPage(List<ReviewResponse> contents, PageCustomRequest pageRequest, long total) {
        super(contents, pageRequest, total);
    }
}
