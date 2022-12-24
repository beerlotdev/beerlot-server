package com.beerlot.domain.auth;

import com.beerlot.domain.auth.dto.response.AccessTokenResponse;
import com.beerlot.domain.member.dto.request.MemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @PatchMapping("/signup")
    ResponseEntity<Void> signUp(@RequestBody MemberRequest memberRequest);

    @GetMapping("/refresh")
    ResponseEntity<AccessTokenResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestHeader("Authorization") String bearerToken);
}
