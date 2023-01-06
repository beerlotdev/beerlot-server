package com.beerlot.domain.policy;

import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.policy.dto.response.PolicyResponse;
import com.beerlot.domain.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PolicyController implements PolicyApi {

    private final PolicyService policyService;

    @Override
    public ResponseEntity<List<PolicyResponse>> findAllPolicies(LanguageType language) {
        LanguageType.validate(language);
        return new ResponseEntity<>(policyService.findAllPolicies(language), HttpStatus.OK);
    }
}
