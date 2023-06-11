package com.beerlot.domain.beer;

import com.beerlot.annotation.CurrentUser;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.common.entity.LanguageType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BeerRecommendApi {
    @Tag(name = "Beer Recommend API", description = "The Beer Recommend API.")
    @GetMapping("/recommend")
    ResponseEntity<List<BeerResponse>> recommendBeer (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @Parameter(description = "Language code") @RequestParam("language") LanguageType language,
            @Parameter(description = "Recommend Amount") @RequestParam(name = "amount", defaultValue = "5") int amount
    );
}
