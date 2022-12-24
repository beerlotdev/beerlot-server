package com.beerlot.domain.common.page;

import java.util.List;

public class PageCustomCustomImpl<T> extends ChunkCustom<T> implements PageCustom<T> {

    private int size;

    private int page;

    private long totalElements;

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getTotalPages() {
        return size == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) size);
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public Integer getNextPage() {
        return page == getTotalPages() ? null : page + 1;
    }

    @Override
    public PageCustomRequest getPageRequest() {
        return pageRequest;
    }

    public PageCustomCustomImpl(List<T> contents, PageCustomRequest pageRequest, long totalElements) {
        super(contents, pageRequest);

        this.size = pageRequest.getSize();
        this.page = pageRequest.getPage();
        this.totalElements = totalElements;
    }
}
