package com.beerlot.core.domain.common.page;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChunkCustom<T> {

    private List<T> contents = new ArrayList<>();

    protected PageCustomRequest pageRequest;

    public ChunkCustom(List<T> contents, PageCustomRequest pageRequest) {
        this.contents = contents;
        this.pageRequest = pageRequest;
    }
}
