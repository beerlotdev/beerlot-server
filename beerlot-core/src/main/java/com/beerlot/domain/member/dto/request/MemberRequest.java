package com.beerlot.domain.member.dto.request;

import com.beerlot.domain.policy.PolicyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class MemberRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("status_message")
    private String statusMessage;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("agreed_policies")
    private Set<PolicyType> agreedPolicies;

    @Builder
    public MemberRequest(String username, String statusMessage, String imageUrl, Set<PolicyType> agreedPolicies) {
        this.username = username;
        this.statusMessage = statusMessage;
        this.imageUrl = imageUrl;
        this.agreedPolicies = agreedPolicies;
    }
}
