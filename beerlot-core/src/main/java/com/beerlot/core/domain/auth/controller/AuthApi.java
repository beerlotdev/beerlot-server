package com.beerlot.core.domain.auth.controller;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.dto.response.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @PatchMapping("/signup")
    ResponseEntity<Void> signUp(@RequestBody MemberCreateRequest memberCreateRequest);

    @GetMapping("/refresh")
    ResponseEntity<AccessTokenResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestHeader("Authorization") String bearerToken);
}
