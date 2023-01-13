package com.beerlot.domain.member.dto.response;

import com.beerlot.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("image_url")
    private String imageUrl;

    @Builder
    public MemberResponse (Long id, String username, String imageUrl) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public static MemberResponse of (Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .imageUrl(member.getImageUrl())
                .build();
    }
}
