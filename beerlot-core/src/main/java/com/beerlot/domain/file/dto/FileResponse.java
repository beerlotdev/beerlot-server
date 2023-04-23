package com.beerlot.domain.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FileResponse {

    @JsonProperty("urls")
    private List<String> urls = new ArrayList<>();

    public void addUrl(String url) {
        this.urls.add(url);
    }
}