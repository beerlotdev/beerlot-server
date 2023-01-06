package com.beerlot.domain.policy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class PolicyResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("is_required")
    private boolean isRequired;

    @Builder
    public PolicyResponse (String name, String url, boolean isRequired) {
        this.name = name;
        this.url = url;
        this.isRequired = isRequired;
    }
}
