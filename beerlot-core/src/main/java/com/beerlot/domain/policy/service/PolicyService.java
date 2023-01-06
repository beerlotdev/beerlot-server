package com.beerlot.domain.policy.service;

import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.policy.PolicyType;
import com.beerlot.domain.policy.dto.response.PolicyResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyService {
    public List<PolicyResponse> findAllPolicies(LanguageType languageType) {
        return Arrays.stream(PolicyType.values())
                .map(policy -> {
                    PolicyResponse.PolicyResponseBuilder builder = PolicyResponse.builder()
                            .url(policy.getUrl())
                            .isRequired(policy.isRequired());
                    if (languageType.name().equals("KR")) {
                        builder.name(policy.getNameKr());
                    } else if (languageType.name().equals("EN")) {
                        builder.name(policy.getNameEn());
                    }
                    return builder.build();
                })
                .collect(Collectors.toList());
    }
}
