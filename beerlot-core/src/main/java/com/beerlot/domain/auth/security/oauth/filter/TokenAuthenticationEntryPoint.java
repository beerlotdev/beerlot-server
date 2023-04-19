package com.beerlot.domain.auth.security.oauth.filter;

import com.beerlot.exception.ErrorMessage;
import com.beerlot.exception.UnauthorizedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        throw new UnauthorizedException(ErrorMessage.NO_AUTHORIZATION.getMessage());
    }
}
