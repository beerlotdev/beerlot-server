package com.beerlot.domain.policy;

import com.beerlot.exception.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum PolicyType {
    PERSONAL_INFORMATION_POLICY(
            "개인정보 처리방침",
            "Personal Information Policy",
            "https://www.notion.so/ff9718b911174d14a58e1a13a60e3d8d",
            true),
    TERMS_OF_SERVICE(
            "이용약관",
            "Terms of Service",
            "https://www.notion.so/57f1ed6bc03841fbbc85dc2d1cb44737",
            true);

    private final String nameKr;
    private final String nameEn;
    private final String url;
    private final boolean isRequired;

    public static void validateAgreeOnRequiredPolicies(Set<PolicyType> agreedPolicies) {
        Set<PolicyType> requiredPolicies = Arrays.stream(values())
                .filter(policy -> policy.isRequired == true)
                .collect(Collectors.toSet());
        if (!agreedPolicies.containsAll(requiredPolicies)) {
            throw new IllegalStateException(ErrorMessage.POLICY__REQUIRED_NOT_AGREED.getMessage());

        }
    }
}
