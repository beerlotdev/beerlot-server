package com.beerlot.domain.beer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Beer Like API", description = "The Beer Like API.")
@RequestMapping("/api/v1/beers")
public interface BeerLikeApi {

    @Operation(description = "Like the beer")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Beer does not exist."),
                    @ApiResponse(responseCode = "409", description = "Member already liked the beer.")
            }
    )
    @PostMapping("/{beerId}/likes")
    ResponseEntity<Void> createBeerLike (
            @Parameter(description = "Beer ID") @PathVariable("beerId") Long beerId
    );

    @Operation(description = "Unlike the beer")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Beer does not exist or Member has not liked the beer yet.")
            }
    )
    @DeleteMapping("/{beerId}/likes")
    ResponseEntity<Void> deleteBeerLike (
            @Parameter(description = "Beer ID") @PathVariable("beerId") Long beerId
    );
}