package com.beerlot.domain.auth;

import com.beerlot.annotation.CurrentUser;
import com.beerlot.domain.auth.dto.response.AccessTokenResponse;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Auth API", description = "The Authorization API.")
@RequestMapping("/api/v1/auth")
public interface AuthApi {
    @Operation(description = "Sign up")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist."),
                    @ApiResponse(responseCode = "409", description = "Member already signed up.")
            }
    )
    @PatchMapping("/signup")
    ResponseEntity<Void> signUp (
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request form for creating member")
            @RequestBody MemberRequest memberRequest
    );

    @Operation(description = "Refresh tokens")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "400", description = "Token is not expired yet or token is invalid."),
                    @ApiResponse(responseCode = "401", description = "No Authorization was found."),
                    @ApiResponse(responseCode = "403", description = "Member does not have proper rights."),
                    @ApiResponse(responseCode = "404", description = "Member does not exist.")
            }
    )
    @GetMapping("/refresh")
    ResponseEntity<AccessTokenResponse> refreshToken (
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(hidden = true) HttpServletResponse response,
            @Parameter(hidden = true) @CurrentUser OAuthUserPrincipal userPrincipal,
            @Parameter(hidden = true) @RequestHeader("Authorization") String bearerToken);
}
