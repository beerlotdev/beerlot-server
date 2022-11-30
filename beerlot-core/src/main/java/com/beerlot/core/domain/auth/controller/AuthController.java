package com.beerlot.core.domain.auth.controller;

import com.beerlot.api.generated.api.AuthApi;
import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.oauth.OAuthProvider;
import com.beerlot.core.domain.auth.ProviderType;
import com.beerlot.core.domain.auth.service.AuthService;
import com.beerlot.core.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;

    @Autowired
    private Map<String, OAuthProvider> oAuthProviders;

    @Override
    public ResponseEntity<Void> authorize(String providerType) {
        ProviderType _providerType = ProviderType.valueOf(providerType.toUpperCase());
        String location = null;

        switch(_providerType) {
            case GOOGLE:
                location = authService.redirect(oAuthProviders.get("GoogleOAuthProvider"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @Override
    public ResponseEntity<Void> login(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String providerType, String code) {
        ProviderType _providerType = ProviderType.valueOf(providerType.toUpperCase());

        String location = null;

        switch(_providerType) {
            case GOOGLE:
                location = authService.login(request, response, oAuthProviders.get("GoogleOAuthProvider"), code);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @Override
    public ResponseEntity<Void> signUp(HttpServletRequest request,
                                       HttpServletResponse response,
                                       MemberCreateRequest memberCreateRequest) {
        try {
            String location = authService.signUp(request, response, memberCreateRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", location);

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch(ConflictException e) {
            return new ResponseEntity<>(e.getErrorCode().getStatus());
        }
    }
}
