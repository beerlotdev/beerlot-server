package com.beerlot.domain.auth.security.oauth.filter;

import com.beerlot.exception.ErrorMessage;
import com.beerlot.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        throw new UnauthorizedException(ErrorMessage.NO_AUTHORIZATION.getMessage());
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), ErrorMessage.NO_AUTHORIZATION.getMessage());
    }
}
