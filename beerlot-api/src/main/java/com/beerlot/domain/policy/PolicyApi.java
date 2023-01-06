package com.beerlot.domain.policy;

import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.policy.dto.response.PolicyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Policy API", description = "The Policy API.")
@RequestMapping("/api/v1/policies")
public interface PolicyApi {

    @Operation(description = "Get all policies")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "400", description = "Language code is invalid or not supported.")
            }
    )

    @GetMapping
    ResponseEntity<List<PolicyResponse>> findAllPolicies (
            @Parameter(description = "Language code") @RequestParam("language") LanguageType language
    );
}
