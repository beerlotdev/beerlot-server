package com.beerlot.domain.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileResponse {

    @JsonProperty("url")
    private String url;

    @Builder
    public FileResponse(String url) {
        this.url = url;
    }

    public static FileResponse of (String url) {
        return FileResponse.builder().url(url).build();
    }
}
