package com.beerlot.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberUsernameRequest {
    @JsonProperty("username")
    @NotNull
    private String username;

    @Builder
    public MemberUsernameRequest(String username) {
        this.username = username;
    }
}
