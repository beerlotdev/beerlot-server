package com.beerlot.core.domain.common.page;

import lombok.Getter;

@Getter
public class PageCustomRequest {

    private int page;

    private int size;

    public PageCustomRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public long getOffset() {
        return (long)(page - 1) * (long)size;
    }
}
