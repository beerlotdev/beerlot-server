package com.beerlot.domain.member;

import com.beerlot.annotation.CurrentUser;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.beer.BeerSortType;
import com.beerlot.domain.beer.dto.response.BeerSimpleResponse;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustom;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.response.ReviewArchiveResponse;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "The Member API.")
@RequestMapping("/api/v1/members")
public interface MemberApi {

    @Operation(description = "Get member profile")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member has no proper roles."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist.")
            }
    )
    @GetMapping("/me")
    ResponseEntity<MemberResponse> getMemberProfile (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal
    );

    @Operation(description = "Update member profile")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member has no proper roles."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist.")
            }
    )
    @PutMapping("/me")
    ResponseEntity<MemberResponse> updateMemberProfile (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request form for updating profile")
            @RequestBody MemberRequest memberRequest
    );

    @Operation(description = "Get all reviews written by the member")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member has no proper roles."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist.")
            }
    )
    @GetMapping("/reviews")
    ResponseEntity<PageCustom<ReviewArchiveResponse>> getAllReviews (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") ReviewSortType sort,
            @Parameter(description = "Language code") @RequestParam("language") LanguageType language
    );

    @Operation(description = "Get all beers that the member likes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member has no proper roles."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist.")
            }
    )
    @GetMapping("/beers")
    ResponseEntity<PageCustom<BeerSimpleResponse>> getAllBeers (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") BeerSortType sort,
            @Parameter(description = "Language code") @RequestParam("language") LanguageType language
    );
}
