package com.beerlot.core.domain.review.util.page;

import com.beerlot.api.generated.model.ReviewResponse;
import com.beerlot.core.domain.common.page.PageCustomImpl;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import lombok.Getter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class ReviewPage extends PageCustomImpl<ReviewResponse> {
    public ReviewPage(List<ReviewResponse> contents, PageCustomRequest pageCustomRequest, long total) {
        super(contents, pageCustomRequest, total);
    }
}
