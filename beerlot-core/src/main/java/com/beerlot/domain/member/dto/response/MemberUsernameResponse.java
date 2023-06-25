package com.beerlot.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUsernameResponse {
    @JsonProperty("username")
    private String username;

    @Builder
    public MemberUsernameResponse(String username) {
        this.username = username;
    }

    public static MemberUsernameResponse of (String username) {
        return MemberUsernameResponse.builder()
                .username(username)
                .build();
    }
}
