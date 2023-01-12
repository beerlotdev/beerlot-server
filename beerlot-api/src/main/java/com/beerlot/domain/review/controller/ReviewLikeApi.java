package com.beerlot.domain.review.controller;

import com.beerlot.annotation.CurrentUser;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface ReviewLikeApi {

    @Tag(name = "Review Like API", description = "The Review Like API.")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Like the review")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Review does not exist."),
                    @ApiResponse(responseCode = "409", description = "Member already liked the review.")
            }
    )
    @PostMapping("/reviews/{reviewId}/likes")
    @PreAuthorize("hasRole('MEMBER')")
    ResponseEntity<Void> createReviewLike (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @Parameter(description = "Review ID") @PathVariable("reviewId") Long reviewId
    );


    @Tag(name = "Review Like API", description = "The Review Like API.")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(description = "Unlike the review")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Review does not exist or Member has not liked the review yet.")
            }
    )
    @DeleteMapping("/reviews/{reviewId}/likes")
    @PreAuthorize("hasRole('MEMBER')")
    ResponseEntity<Void> deleteReviewLike (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @Parameter(description = "Review ID") @PathVariable("reviewId") Long reviewId
    );
}
