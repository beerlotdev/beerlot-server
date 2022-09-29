package com.beerlot.core.domain.common.page;

import com.beerlot.core.domain.common.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageCustomRequest {

    private int page;

    private int size;

    private SortType sort;

    public PageCustomRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public long getOffset() {
        return (long)(page - 1) * (long)size;
    }
}
