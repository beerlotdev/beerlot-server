package com.beerlot.core.domain.common.page;

import java.util.List;

public interface PageCustom<T> {

    int getPage();

    int getTotalPages();

    long getTotalElements();

    Integer getNextPage();

    List<T> getContents();

    PageCustomRequest getPageRequest();
}
