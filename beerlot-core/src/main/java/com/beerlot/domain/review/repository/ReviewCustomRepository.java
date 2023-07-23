package com.beerlot.domain.review.repository;

import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.review.dto.response.ReviewArchiveResponse;
import com.beerlot.domain.review.dto.response.ReviewResponse;

public interface ReviewCustomRepository {
    PageCustom<ReviewResponse> findByBeerId(Long beerId, PageCustomRequest pageRequest);
    PageCustom<ReviewResponse> findAll(PageCustomRequest pageRequest, LanguageType language);

    PageCustom<ReviewArchiveResponse> findByMember(String oauthId, PageCustomRequest pageRequest, LanguageType language);
}
